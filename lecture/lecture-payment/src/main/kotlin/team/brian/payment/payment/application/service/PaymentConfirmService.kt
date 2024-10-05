package team.brian.payment.payment.application.service

import reactor.core.publisher.Mono
import team.brian.payment.common.UseCase
import team.brian.payment.payment.application.port.inbound.PaymentConfirmCommand
import team.brian.payment.payment.application.port.inbound.PaymentConfirmUseCase
import team.brian.payment.payment.application.port.outbound.PaymentExecutorPort
import team.brian.payment.payment.application.port.outbound.PaymentStatusUpdatePort
import team.brian.payment.payment.application.port.outbound.PaymentValidationPort
import team.brian.payment.payment.domain.paymentConfirmation.PaymentConfirmationResult

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
@UseCase
class PaymentConfirmService(
    private val paymentStatusUpdatePort: PaymentStatusUpdatePort,
    private val paymentValidationPort: PaymentValidationPort,
    private val paymentExecutorPort: PaymentExecutorPort,
) : PaymentConfirmUseCase {
    override fun confirm(command: PaymentConfirmCommand): Mono<PaymentConfirmationResult> {
        paymentStatusUpdatePort.updatePaymentStatusToExecuting(command.orderId, command.paymentKey)
            .filterWhen { paymentValidationPort.isValid(command.orderId, command.amount) } // 유효한 결제에 대해서만 다음 단계를 처리한다
            .flatMap { paymentExecutorPort.execute(command) }
            .flatMap { }
    }
}
