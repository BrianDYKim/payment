package personal.brian.payment.presentation.view.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Controller
@RequestMapping("/payment/v1/toss")
class PaymentViewController {
    @GetMapping("/fail")
    fun failPage() = "fail"

    @GetMapping("/success")
    fun successPage() = "success"
}
