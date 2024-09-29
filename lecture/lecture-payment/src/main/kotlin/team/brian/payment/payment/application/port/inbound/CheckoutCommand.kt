package team.brian.payment.payment.application.port.inbound

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
data class CheckoutCommand(
    val cartId: Long,
    val buyerId: Long,
    val productIdList: List<Long>,
    val idempotencyKey: String,
)
