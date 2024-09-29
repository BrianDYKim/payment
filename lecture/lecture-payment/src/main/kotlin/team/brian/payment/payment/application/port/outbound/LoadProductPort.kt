package team.brian.payment.payment.application.port.outbound

import reactor.core.publisher.Flux
import team.brian.payment.payment.domain.product.Product

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
interface LoadProductPort {
    fun getProductList(
        cartId: Long,
        productIdList: List<Long>,
    ): Flux<Product>
}
