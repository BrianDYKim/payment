package team.brian.payment.payment.adapter.outbound.web.toss.executor

import reactor.core.publisher.Mono
import team.brian.payment.payment.application.port.inbound.PaymentConfirmCommand
import team.brian.payment.payment.domain.paymentExecution.PaymentExecutionResult

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
interface PaymentExecutor {
    fun execute(command: PaymentConfirmCommand): Mono<PaymentExecutionResult>
}
