package personal.brian.payment.application.useCase

import personal.brian.payment.domain.domain.paymentEvent.repository.PaymentEventRepository
import personal.brian.payment.domain.domain.paymentEvent.root.PaymentEvent
import personal.brian.springExtension.annotation.UseCase

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@UseCase
class SavePaymentEventUseCase(
    private val paymentEventRepository: PaymentEventRepository,
) {
    fun save(paymentEvent: PaymentEvent): PaymentEvent {
        return paymentEventRepository.save(paymentEvent)
    }
}
