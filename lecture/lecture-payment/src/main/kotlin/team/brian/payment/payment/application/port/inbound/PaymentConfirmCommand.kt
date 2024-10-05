package team.brian.payment.payment.application.port.inbound

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
data class PaymentConfirmCommand(
    val paymentKey: String,
    val orderId: String,
    val amount: Long,
)
