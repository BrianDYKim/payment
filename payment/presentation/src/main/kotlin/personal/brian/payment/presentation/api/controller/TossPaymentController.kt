package personal.brian.payment.presentation.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import personal.brian.payment.application.dto.TossPaymentConfirmDto
import personal.brian.payment.application.operation.command.TossPaymentConfirmCommand
import personal.brian.payment.application.service.PaymentService
import personal.brian.payment.pspToss.payload.PaymentExecutionResult

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@RestController
@RequestMapping("/payment/v1/toss")
class TossPaymentController(
    private val paymentService: PaymentService,
) {
    @PostMapping("/confirm")
    fun confirm(
        @RequestBody request: TossPaymentConfirmDto.Request,
    ): PaymentExecutionResult {
        val tossPaymentConfirmationCommand = TossPaymentConfirmCommand.from(request)

        return paymentService.confirmTossPayment(tossPaymentConfirmationCommand)
    }
}
