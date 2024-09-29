package team.brian.payment.payment.domain.checkoutResult

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
data class CheckoutResult(
    val amount: Long,
    val orderId: String,
    val orderName: String,
)
