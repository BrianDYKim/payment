package team.brian.payment.payment.domain.paymentEvent

import java.math.BigDecimal

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
data class PaymentOrder(
    val id: Long? = null,
    val paymentEventId: Long? = null,
    val sellerId: Long,
    val buyerId: Long,
    val productId: Long,
    val orderId: String,
    val amount: BigDecimal,
    val paymentStatus: PaymentStatus,
    private var isLedgerUpdated: Boolean = false,
    private var isWalletUpdated: Boolean = false,
) {
    fun isLedgerUpdated() = isLedgerUpdated

    fun isWalletUpdated() = isWalletUpdated
}
