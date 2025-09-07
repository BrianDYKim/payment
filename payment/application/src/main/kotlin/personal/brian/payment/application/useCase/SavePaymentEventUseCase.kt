package personal.brian.payment.application.useCase

import personal.brian.payment.domain.domain.paymentEvent.root.PaymentEvent
import personal.brian.springExtension.annotation.UseCase

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@UseCase
class SavePaymentEventUseCase {
    fun save(paymentEvent: PaymentEvent): PaymentEvent {
        TODO("Not yet implemented")
    }
}
