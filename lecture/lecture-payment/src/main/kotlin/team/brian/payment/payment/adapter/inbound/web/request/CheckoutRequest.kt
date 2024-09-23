package team.brian.payment.payment.adapter.inbound.web.request

import java.time.LocalDateTime

/**
 * @author Brian
 * @since 2024. 9. 23.
 */
data class CheckoutRequest(
    val cartId: Long = 1,
    val productIdList: List<Long> = listOf(1, 2, 3),
    val buyerId: Long = 1,
    val seed: String = LocalDateTime.now().toString(),
)
