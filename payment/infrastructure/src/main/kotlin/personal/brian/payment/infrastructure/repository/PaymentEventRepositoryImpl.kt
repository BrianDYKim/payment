package personal.brian.payment.infrastructure.repository

import org.springframework.stereotype.Repository
import personal.brian.payment.domain.domain.paymentEvent.repository.PaymentEventRepository
import personal.brian.payment.domain.domain.paymentEvent.root.PaymentEvent
import personal.brian.payment.externals.rdb.table.paymentEvent.repository.PaymentEventJpaRepository
import personal.brian.payment.externals.rdb.table.paymentOrder.repository.PaymentOrderJpaRepository
import personal.brian.payment.infrastructure.mapper.PaymentEventMapper
import personal.brian.payment.infrastructure.mapper.PaymentOrderMapper

/**
 * @author Doyeop Kim
 * @since 2025. 9. 14.
 */
@Repository
class PaymentEventRepositoryImpl(
    private val paymentEventJpaRepository: PaymentEventJpaRepository,
    private val paymentOrderJpaRepository: PaymentOrderJpaRepository,
    private val paymentEventMapper: PaymentEventMapper,
    private val paymentOrderMapper: PaymentOrderMapper,
) : PaymentEventRepository {
    override fun save(paymentEvent: PaymentEvent): PaymentEvent {
        // paymentEvent 영속화
        val paymentEventJpaEntity = paymentEventMapper.transformToJpaEntityForCheckout(paymentEvent)
        val savedPaymentEventJpaEntity = paymentEventJpaRepository.save(paymentEventJpaEntity)
        val savedPaymentEvent = paymentEvent.substituteIdentifier(savedPaymentEventJpaEntity.id)

        // paymentOrder 영속화
        val eventId = savedPaymentEvent.id
        paymentEvent.paymentOrderList.map { it -> paymentOrderMapper.transformToJpaEntityForCheckout(eventId, it) }
            .forEach(paymentOrderJpaRepository::save)

        return savedPaymentEvent
    }
}
