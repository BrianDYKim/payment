package personal.brian.payment.domain.domain.paymentEvent.repository

import personal.brian.payment.domain.domain.paymentEvent.root.PaymentEvent

/**
 * @author Doyeop Kim
 * @since 2025. 9. 14.
 */
interface PaymentEventRepository {
    fun save(paymentEvent: PaymentEvent): PaymentEvent
}
