package personal.brian.payment.externals.rdb.table.paymentEvent.repository

import org.springframework.data.jpa.repository.JpaRepository
import personal.brian.payment.externals.rdb.table.paymentEvent.entity.PaymentEventJpaEntity

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
interface PaymentEventJpaRepository : JpaRepository<PaymentEventJpaEntity, Long>, PaymentEventJpaCustomRepository
