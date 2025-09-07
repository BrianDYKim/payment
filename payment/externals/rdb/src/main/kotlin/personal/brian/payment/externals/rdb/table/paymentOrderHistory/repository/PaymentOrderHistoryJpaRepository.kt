package personal.brian.payment.externals.rdb.table.paymentOrderHistory.repository

import org.springframework.data.jpa.repository.JpaRepository
import personal.brian.payment.externals.rdb.table.paymentOrderHistory.entity.PaymentOrderHistoryJpaEntity

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
interface PaymentOrderHistoryJpaRepository : JpaRepository<PaymentOrderHistoryJpaEntity, Long>, PaymentOrderHistoryJpaCustomRepository
