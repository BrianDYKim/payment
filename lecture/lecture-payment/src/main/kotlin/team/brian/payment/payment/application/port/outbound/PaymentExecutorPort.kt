package team.brian.payment.payment.application.port.outbound

import reactor.core.publisher.Mono
import team.brian.payment.payment.application.port.inbound.PaymentConfirmCommand
import team.brian.payment.payment.domain.paymentExecution.PaymentExecutionResult

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
interface PaymentExecutorPort {
    fun execute(command: PaymentConfirmCommand): Mono<PaymentExecutionResult>
}
