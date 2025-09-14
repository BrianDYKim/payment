package personal.brian.payment.infrastructure.mapper

import org.springframework.stereotype.Component
import personal.brian.payment.domain.domain.paymentEvent.root.PaymentEvent
import personal.brian.payment.externals.rdb.table.paymentEvent.entity.PaymentEventJpaEntity

/**
 * @author Doyeop Kim
 * @since 2025. 9. 14.
 */
@Component
class PaymentEventMapper {
    fun transformToJpaEntityForCheckout(paymentEvent: PaymentEvent): PaymentEventJpaEntity {
        return PaymentEventJpaEntity.new(
            buyerId = paymentEvent.buyerId,
            orderId = paymentEvent.orderId,
            orderName = paymentEvent.orderName,
        )
    }
}
