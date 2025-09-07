package personal.brian.payment.application.useCase

import personal.brian.payment.domain.domain.product.repository.ProductRepository
import personal.brian.payment.domain.domain.product.root.Product
import personal.brian.springExtension.annotation.UseCase

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@UseCase
class FetchProductUseCase(
    private val productRepository: ProductRepository,
) {
    fun getProducts(
        cartId: Long,
        productIdList: List<Long>,
    ): List<Product> {
        return productRepository.getProducts(cartId, productIdList)
    }
}
