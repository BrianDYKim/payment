package team.brian.payment.payment.adapter.outbound.web.product.client

import reactor.core.publisher.Flux
import team.brian.payment.payment.domain.product.Product

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
interface ProductClient {
    fun getProducts(
        cartId: Long,
        productIdList: List<Long>,
    ): Flux<Product>
}
