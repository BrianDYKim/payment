package personal.brian.payment.domain.domain.product.root

import personal.brian.payment.domain.price.Price

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
data class Product(
    val id: Long,
    val price: Price,
    val quantity: Int,
    val name: String,
    val sellerId: Long,
) {
    companion object {
        fun of(
            id: Long,
            price: Price,
            quantity: Int,
            name: String,
            sellerId: Long,
        ) = Product(id, price, quantity, name, sellerId)
    }
}
