package personal.brian.payment.domain.domain.paymentEvent.entity

import personal.brian.payment.domain.payment.enums.PaymentStatus
import personal.brian.payment.domain.price.Price

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
data class PaymentOrder(
    val id: Long,
    val sellerId: Long,
    val buyerId: Long,
    val productId: Long,
    val orderId: String,
    val price: Price,
    val paymentStatus: PaymentStatus,
    private var isLedgerUpdated: Boolean = false,
    private var isWalletUpdated: Boolean = false,
) {
    companion object {
        fun new(
            sellerId: Long,
            buyerId: Long,
            productId: Long,
            orderId: String,
            price: Price,
        ) = PaymentOrder(
            id = 0L,
            sellerId = sellerId,
            buyerId = buyerId,
            productId = productId,
            orderId = orderId,
            price = price,
            paymentStatus = PaymentStatus.NOT_STARTED,
        )
    }
}
