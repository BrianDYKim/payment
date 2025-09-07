package personal.brian.payment.domain.domain.product.repository

import personal.brian.payment.domain.domain.product.root.Product

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
interface ProductRepository {
    fun getProducts(
        cartId: Long,
        productIdList: List<Long>,
    ): List<Product>
}
