package personal.brian.payment.infrastructure.repository

import org.springframework.stereotype.Repository
import personal.brian.payment.domain.domain.product.repository.ProductRepository
import personal.brian.payment.domain.domain.product.root.Product
import personal.brian.payment.domain.price.Currency
import personal.brian.payment.domain.price.Price
import java.math.BigDecimal

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@Repository
class ProductRepositoryImpl : ProductRepository {
    override fun getProducts(
        cartId: Long,
        productIdList: List<Long>,
    ): List<Product> {
        return productIdList.map {
            Product.of(
                id = it,
                price = Price.of(BigDecimal.valueOf(10000), Currency.KRW),
                quantity = 2,
                name = "test product $it",
                sellerId = 1,
            )
        }
    }
}
