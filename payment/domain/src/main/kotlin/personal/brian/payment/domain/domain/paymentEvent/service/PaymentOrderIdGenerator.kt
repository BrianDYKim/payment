package personal.brian.payment.domain.domain.paymentEvent.service

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
object PaymentOrderIdGenerator {
    fun generateOrderId(
        index: Int,
        parentOrderId: String,
    ): String {
        return "$parentOrderId.A$index"
    }
}
