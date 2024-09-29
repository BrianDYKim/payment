package team.brian.payment.payment.adapter.outbound.persistent

import reactor.core.publisher.Mono
import team.brian.payment.common.PersistentAdapter
import team.brian.payment.payment.adapter.outbound.persistent.repository.PaymentRepository
import team.brian.payment.payment.application.port.outbound.SavePaymentPort
import team.brian.payment.payment.domain.paymentEvent.PaymentEvent

@PersistentAdapter
class PaymentPersistentAdapter(
    private val paymentRepository: PaymentRepository,
) : SavePaymentPort {
    override fun save(paymentEvent: PaymentEvent): Mono<Void> {
        return paymentRepository.save(paymentEvent)
    }
}
