package team.brian.payment.payment.adapter.inbound.web.request

/**
 * @author Doyeop Kim
 * @since 2024. 9. 22.
 */
data class TossPaymentConfirmRequest(
    val paymentKey: String,
    val orderId: String,
    val amount: Long,
)
