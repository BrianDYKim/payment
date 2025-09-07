package personal.brian.payment.presentation.view.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import personal.brian.payment.application.dto.CheckoutDto
import personal.brian.payment.application.operation.command.CheckoutCommand
import personal.brian.payment.application.service.CheckoutService

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Controller
@RequestMapping("/checkout/v1/toss")
class CheckoutViewController(
    private val checkoutService: CheckoutService,
) {
    @GetMapping("")
    fun checkoutPage(
        request: CheckoutDto.Request,
        model: Model,
    ): String {
        val checkoutCommand = CheckoutCommand.from(request)

        val responsePayload = checkoutService.checkout(checkoutCommand)

        model.apply {
            addAttribute("orderId", responsePayload.orderId)
            addAttribute("orderName", responsePayload.orderName)
            addAttribute("amount", responsePayload.amount)
        }

        return "checkout"
    }
}
