package personal.brian.payment.application.dto

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
sealed class TossPaymentConfirmDto {
    data class Request(
        val paymentKey: String,
        val orderId: String,
        val amount: String,
    )
}
