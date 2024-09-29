package team.brian.payment.payment.adapter.inbound.web.view

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono
import team.brian.payment.common.IdempotencyCreator
import team.brian.payment.common.WebAdapter
import team.brian.payment.payment.adapter.inbound.web.request.CheckoutRequest
import team.brian.payment.payment.application.port.inbound.CheckoutCommand
import team.brian.payment.payment.application.port.inbound.CheckoutUseCase

/**
 * @author Doyeop Kim
 * @since 2024. 9. 22.
 */
@WebAdapter
@Controller
class CheckoutController(
    private val checkoutUseCase: CheckoutUseCase,
) {
    @GetMapping("/")
    fun checkoutPage(
        request: CheckoutRequest,
        model: Model,
    ): Mono<String> {
        val checkoutCommand =
            with(request) {
                CheckoutCommand(
                    cartId = cartId,
                    buyerId = buyerId,
                    productIdList = productIdList,
                    idempotencyKey = IdempotencyCreator.create(seed),
                )
            }

        return checkoutUseCase.checkout(checkoutCommand)
            .map {
                with(model) {
                    addAttribute("orderId", it.orderId)
                    addAttribute("orderName", it.orderName)
                    addAttribute("amount", it.amount)
                    "checkout"
                }
            }
    }
}
