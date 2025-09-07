package personal.brian.payment.application.operation.command

import personal.brian.payment.application.dto.CheckoutDto
import personal.brian.payment.domain.helper.IdempotencyKeyGenerator

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
data class CheckoutCommand(
    val cartId: Long,
    val productIdList: List<Long>,
    val buyerId: Long,
    val idempotencyKey: String,
) {
    companion object {
        fun from(request: CheckoutDto.Request) =
            CheckoutCommand(
                cartId = request.cartId,
                productIdList = request.productIdList,
                buyerId = request.buyerId,
                idempotencyKey = IdempotencyKeyGenerator.create(request.seed),
            )
    }
}
