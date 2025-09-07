package personal.brian.payment.externals.rdb.table.paymentOrder.repository

import org.springframework.data.jpa.repository.JpaRepository
import personal.brian.payment.externals.rdb.table.paymentOrder.entity.PaymentOrderJpaEntity

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
interface PaymentOrderJpaRepository : JpaRepository<PaymentOrderJpaEntity, Long>, PaymentOrderJpaCustomRepository
