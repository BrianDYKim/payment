package personal.brian.payment.infrastructure.mapper

import org.springframework.stereotype.Component
import personal.brian.payment.domain.domain.paymentEvent.entity.PaymentOrder
import personal.brian.payment.externals.rdb.table.paymentOrder.entity.PaymentOrderJpaEntity

/**
 * @author Doyeop Kim
 * @since 2025. 9. 14.
 */
@Component
class PaymentOrderMapper {
    fun transformToJpaEntityForCheckout(
        paymentEventId: Long,
        paymentOrder: PaymentOrder,
    ): PaymentOrderJpaEntity {
        return PaymentOrderJpaEntity.new(
            paymentEventId = paymentEventId,
            sellerId = paymentOrder.sellerId,
            productId = paymentOrder.productId,
            orderId = paymentOrder.orderId,
            price = paymentOrder.price,
            status = paymentOrder.paymentStatus,
        )
    }
}
