package team.brian.payment.payment.adapter.outbound.persistent

import reactor.core.publisher.Mono
import team.brian.payment.common.PersistentAdapter
import team.brian.payment.payment.adapter.outbound.persistent.repository.PaymentRepository
import team.brian.payment.payment.adapter.outbound.persistent.repository.PaymentStatusUpdateRepository
import team.brian.payment.payment.adapter.outbound.persistent.repository.PaymentValidationRepository
import team.brian.payment.payment.application.port.outbound.PaymentStatusUpdatePort
import team.brian.payment.payment.application.port.outbound.PaymentValidationPort
import team.brian.payment.payment.application.port.outbound.SavePaymentPort
import team.brian.payment.payment.domain.paymentEvent.PaymentEvent

@PersistentAdapter
class PaymentPersistentAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentStatusUpdateRepository: PaymentStatusUpdateRepository,
    private val paymentValidationRepository: PaymentValidationRepository,
) : SavePaymentPort, PaymentStatusUpdatePort, PaymentValidationPort {
    override fun save(paymentEvent: PaymentEvent): Mono<Void> {
        return paymentRepository.save(paymentEvent)
    }

    override fun updatePaymentStatusToExecuting(
        orderId: String,
        paymentKey: String,
    ): Mono<Boolean> {
        return paymentStatusUpdateRepository.updatePaymentStatusToExecuting(orderId, paymentKey)
    }

    override fun isValid(
        orderId: String,
        amount: Long,
    ): Mono<Boolean> {
        return paymentValidationRepository.isValid(orderId, amount)
    }
}
