package team.brian.payment.payment.domain.product

import java.math.BigDecimal

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
data class Product(
    val id: Long,
    val amount: BigDecimal,
    val quantity: Int,
    val name: String,
    val sellerId: Long,
)
