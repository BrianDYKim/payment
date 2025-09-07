package personal.brian.payment.domain.payment.payload

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
data class PaymentFailure(
    val errorCode: String,
    val message: String,
)
