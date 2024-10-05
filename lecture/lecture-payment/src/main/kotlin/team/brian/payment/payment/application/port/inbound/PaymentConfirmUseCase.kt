package team.brian.payment.payment.application.port.inbound

import reactor.core.publisher.Mono
import team.brian.payment.payment.domain.paymentConfirmation.PaymentConfirmationResult

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
interface PaymentConfirmUseCase {
    fun confirm(command: PaymentConfirmCommand): Mono<PaymentConfirmationResult>
}
