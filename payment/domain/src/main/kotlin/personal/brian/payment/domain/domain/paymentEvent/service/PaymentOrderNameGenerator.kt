package personal.brian.payment.domain.domain.paymentEvent.service

import personal.brian.payment.domain.domain.product.root.Product

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
object PaymentOrderNameGenerator {
    fun generateOrderName(productList: List<Product>): String {
        when (productList.size) {
            1 -> {
                val product = productList[0]
                return "${product.name} ${product.quantity}개"
            }

            else -> {
                return productList.joinToString(", ") { "${it.name} ${it.quantity}개" }
            }
        }
    }
}
