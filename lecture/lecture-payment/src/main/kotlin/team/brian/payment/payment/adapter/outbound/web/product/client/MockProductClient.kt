package team.brian.payment.payment.adapter.outbound.web.product.client

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import team.brian.payment.payment.domain.product.Product
import java.math.BigDecimal

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
@Component
class MockProductClient : ProductClient {
    override fun getProducts(
        cartId: Long,
        productIdList: List<Long>,
    ): Flux<Product> {
        return Flux.fromIterable(
            productIdList.map { createProductFromId(it) },
        )
    }

    private fun createProductFromId(id: Long): Product =
        Product(
            id = id,
            amount = BigDecimal(id * 10000),
            quantity = 2,
            name = "test_prouct_$id",
            sellerId = 1,
        )
}
