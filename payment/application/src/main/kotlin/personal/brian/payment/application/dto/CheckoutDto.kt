package personal.brian.payment.application.dto

import personal.brian.payment.domain.price.Price
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
sealed class CheckoutDto {
    data class Request(
        val cartId: Long = 1L,
        val productIdList: List<Long> = listOf(1, 2, 3),
        val buyerId: Long = 1L,
        val seed: String = LocalDateTime.now().toString(),
    )

    data class Response(
        val orderId: String,
        val orderName: String,
        val amount: BigDecimal,
        val currency: String,
    ) {
        companion object {
            fun of(
                orderId: String,
                orderName: String,
                price: Price,
            ): Response {
                return Response(
                    orderId = orderId,
                    orderName = orderName,
                    amount = price.amount,
                    currency = price.currency.name,
                )
            }
        }
    }
}
