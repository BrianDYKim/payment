package team.brian.payment.payment.adapter.inbound.web.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import team.brian.payment.common.WebAdapter
import team.brian.payment.payment.adapter.inbound.web.request.TossPaymentConfirmRequest
import team.brian.payment.payment.adapter.inbound.web.response.ApiResponse
import team.brian.payment.payment.application.port.inbound.PaymentConfirmCommand
import team.brian.payment.payment.application.port.inbound.PaymentConfirmUseCase
import team.brian.payment.payment.domain.paymentConfirmation.PaymentConfirmationResult

/**
 * @author Doyeop Kim
 * @since 2024. 9. 22.
 */
@WebAdapter
@RequestMapping("/v1/toss")
@RestController
class TossPaymentController(
    private val paymentConfirmUseCase: PaymentConfirmUseCase,
) {
    @PostMapping("/confirm")
    fun confirm(
        @RequestBody request: TossPaymentConfirmRequest,
    ): Mono<ResponseEntity<ApiResponse<PaymentConfirmationResult>>> {
        val command =
            PaymentConfirmCommand(
                paymentKey = request.paymentKey,
                orderId = request.orderId,
                amount = request.amount,
            )

        return paymentConfirmUseCase.confirm(command)
            .map { ResponseEntity.ok().body(ApiResponse.with(HttpStatus.OK, "OK", it)) }
    }
}
