package team.brian.payment.payment.adapter.outbound.web.toss

import reactor.core.publisher.Mono
import team.brian.payment.common.WebAdapter
import team.brian.payment.payment.adapter.outbound.web.toss.executor.PaymentExecutor
import team.brian.payment.payment.application.port.inbound.PaymentConfirmCommand
import team.brian.payment.payment.application.port.outbound.PaymentExecutorPort
import team.brian.payment.payment.domain.paymentExecution.PaymentExecutionResult

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
@WebAdapter
class PaymentExecutorWebAdapter(
    private val paymentExecutor: PaymentExecutor,
) : PaymentExecutorPort {
    override fun execute(command: PaymentConfirmCommand): Mono<PaymentExecutionResult> {
        return paymentExecutor.execute(command)
    }
}
