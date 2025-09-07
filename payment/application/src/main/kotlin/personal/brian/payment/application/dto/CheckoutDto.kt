package personal.brian.payment.application.dto

import personal.brian.payment.domain.price.Price
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
sealed class CheckoutDto {
    data class Request(
        val cartId: Long,
        val productIdList: List<Long>,
        val buyerId: Long,
        val seed: String = LocalDateTime.now().toString(),
    )

    data class Response(
        val orderId: String,
        val orderName: String,
        val amount: Price,
    )
}
