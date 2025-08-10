package personal.brian.payment.presentation.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import personal.brian.payment.application.dto.TossPaymentConfirmDto

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@RestController
@RequestMapping("/payment/v1/toss")
class TossPaymentController {
    @PostMapping("/confirm")
    fun confirm(
        @RequestBody request: TossPaymentConfirmDto.Request,
    ): String {
        return "confirm"
    }
}
