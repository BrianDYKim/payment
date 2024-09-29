package team.brian.payment.payment.application.service

import reactor.core.publisher.Mono
import team.brian.payment.common.UseCase
import team.brian.payment.payment.application.port.inbound.CheckoutCommand
import team.brian.payment.payment.application.port.inbound.CheckoutUseCase
import team.brian.payment.payment.application.port.outbound.LoadProductPort
import team.brian.payment.payment.application.port.outbound.SavePaymentPort
import team.brian.payment.payment.domain.checkoutResult.CheckoutResult
import team.brian.payment.payment.domain.paymentEvent.PaymentEvent
import team.brian.payment.payment.domain.paymentEvent.PaymentOrder
import team.brian.payment.payment.domain.paymentEvent.PaymentStatus
import team.brian.payment.payment.domain.product.Product

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
@UseCase
class CheckoutService(
    private val loadProductPort: LoadProductPort,
    private val savePaymentPort: SavePaymentPort,
) : CheckoutUseCase {
    override fun checkout(command: CheckoutCommand): Mono<CheckoutResult> {
        return loadProductPort.getProductList(command.cartId, command.productIdList)
            .collectList()
            .map { createPaymentEvent(command, it) }
            .flatMap { savePaymentPort.save(it).thenReturn(it) }
            .map { CheckoutResult(amount = it.totalAmount(), orderId = it.orderId, orderName = it.orderName) }
    }

    private fun createPaymentEvent(
        command: CheckoutCommand,
        productList: List<Product>,
    ): PaymentEvent =
        with(command) {
            PaymentEvent(
                buyerId = buyerId,
                orderId = idempotencyKey,
                orderName = productList.joinToString { it.name },
                paymentOrders =
                    productList.map {
                        PaymentOrder(
                            sellerId = it.sellerId,
                            orderId = command.idempotencyKey,
                            productId = it.id,
                            amount = it.amount,
                            buyerId = 0,
                            paymentStatus = PaymentStatus.NOT_STARTED,
                        )
                    },
            )
        }
}
