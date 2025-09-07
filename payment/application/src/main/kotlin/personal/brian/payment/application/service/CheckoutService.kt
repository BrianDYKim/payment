package personal.brian.payment.application.service

import org.springframework.stereotype.Service
import personal.brian.payment.application.dto.CheckoutDto
import personal.brian.payment.application.operation.command.CheckoutCommand
import personal.brian.payment.application.useCase.FetchProductUseCase
import personal.brian.payment.application.useCase.ProceedCheckoutUseCase
import personal.brian.payment.application.useCase.SavePaymentEventUseCase
import personal.brian.payment.domain.domain.paymentEvent.entity.PaymentOrder
import personal.brian.payment.domain.domain.paymentEvent.root.PaymentEvent
import personal.brian.payment.domain.domain.paymentEvent.service.PaymentOrderIdGenerator
import personal.brian.payment.domain.domain.paymentEvent.service.PaymentOrderNameGenerator
import personal.brian.payment.domain.domain.product.root.Product
import personal.brian.payment.externals.rdb.transaction.executor.Tx

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@Service
class CheckoutService(
    private val proceedCheckoutUseCase: ProceedCheckoutUseCase,
    private val fetchProductUseCase: FetchProductUseCase,
    private val savePaymentEventUseCase: SavePaymentEventUseCase,
) {
    fun checkout(command: CheckoutCommand): CheckoutDto.Response =
        Tx.writable {
            // 물품들을 조회한다
            val products = fetchProductUseCase.getProducts(command.cartId, command.productIdList)

            // 조회 product 기반으로 event 생성
            val paymentEvent = createPaymentEvent(command, products)

            // payment event 저장
            val savedPaymentEvent = savePaymentEventUseCase.save(paymentEvent)

            // response payload를 조립하여 반환
            return@writable CheckoutDto.Response(
                orderId = savedPaymentEvent.orderId,
                orderName = savedPaymentEvent.orderName,
                amount = savedPaymentEvent.calculateTotalAmount(),
            )
        }

    private fun createPaymentEvent(
        command: CheckoutCommand,
        productList: List<Product>,
    ): PaymentEvent {
        return PaymentEvent.new(
            buyerId = command.buyerId,
            orderId = command.idempotencyKey,
            orderName = PaymentOrderNameGenerator.generateOrderName(productList),
            paymentOrderList =
                productList.mapIndexed { index, product ->
                    PaymentOrder.new(
                        sellerId = product.sellerId,
                        buyerId = command.buyerId,
                        productId = product.id,
                        orderId = PaymentOrderIdGenerator.generateOrderId(index, command.idempotencyKey),
                        price = product.price,
                    )
                },
        )
    }
}
