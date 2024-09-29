package team.brian.payment.payment.adapter.outbound.web.product

import reactor.core.publisher.Flux
import team.brian.payment.common.WebAdapter
import team.brian.payment.payment.adapter.outbound.web.product.client.ProductClient
import team.brian.payment.payment.application.port.outbound.LoadProductPort
import team.brian.payment.payment.domain.product.Product

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
@WebAdapter
class ProductWebAdapter(
    private val productClient: ProductClient,
) : LoadProductPort {
    override fun getProductList(
        cartId: Long,
        productIdList: List<Long>,
    ): Flux<Product> {
        return productClient.getProducts(cartId, productIdList)
    }
}
