package personal.brian.payment.domain.domain.paymentEvent.root

import personal.brian.extension.domain.IdentifierSubstitutable
import personal.brian.payment.domain.domain.paymentEvent.entity.PaymentOrder
import personal.brian.payment.domain.error.ErrorCode
import personal.brian.payment.domain.error.PaymentDomainException
import personal.brian.payment.domain.payment.enums.PaymentMethod
import personal.brian.payment.domain.payment.enums.PaymentType
import personal.brian.payment.domain.price.Price
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
data class PaymentEvent(
    override val id: Long = 0,
    val buyerId: Long,
    val orderId: String,
    val orderName: String,
    val paymentKey: String? = null,
    val paymentType: PaymentType? = null,
    val paymentMethod: PaymentMethod? = null,
    val approvedAt: LocalDateTime? = null,
    val paymentOrderList: List<PaymentOrder> = emptyList(),
    val isPaymentDone: Boolean = false,
) : IdentifierSubstitutable<PaymentEvent, Long> {
    companion object {
        fun new(
            buyerId: Long,
            orderId: String,
            orderName: String,
            paymentOrderList: List<PaymentOrder> = emptyList(),
        ) = PaymentEvent(
            buyerId = buyerId,
            orderId = orderId,
            orderName = orderName,
            paymentOrderList = paymentOrderList,
        )
    }

    override fun substituteIdentifier(id: Long) = copy(id = id)

    fun calculateTotalAmount(): Price {
        require(paymentOrderList.isNotEmpty()) {
            throw PaymentDomainException(
                message = "주문이 존재하지 않습니다.",
                errorCode = ErrorCode.PAYMENT_ORDER_NOT_FOUND,
            )
        }

        val currency = paymentOrderList.first().price.currency
        val initialPrice = Price.zero(currency)

        return paymentOrderList.fold(initialPrice) { acc, paymentOrder ->
            acc + paymentOrder.price
        }
    }
}
