package personal.brian.payment.pspToss.payload

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
data class PaymentConfirmPayload(
    val paymentKey: String,
    val orderId: String,
    val amount: String,
)
