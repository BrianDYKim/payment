package personal.brian.payment.externals.rdb.table.paymentEvent.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import personal.brian.payment.domain.payment.enums.PaymentType
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@Entity
@Table(name = "payment_events")
class PaymentEventJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    @Column(name = "buyer_id", nullable = false)
    var buyerId: Long = 0L,
    @Column(name = "is_payment_done", nullable = false)
    var isPaymentDone: Boolean = false,
    @Column(name = "payment_key", nullable = true)
    var paymentKey: String? = null,
    @Column(name = "order_id", nullable = true)
    var orderId: String? = null,
    @Column(name = "type", nullable = false)
    var type: String = PaymentType.NORMAL.name,
    @Column(name = "order_name", nullable = false)
    var orderName: String = "",
    @Column(name = "method", nullable = true)
    var method: String? = null,
    @Column(name = "psp_raw_data", nullable = true)
    var pspRawData: String? = null,
    @Column(name = "approved_at", nullable = true)
    var approvedAt: LocalDateTime? = null,
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun new(
            buyerId: Long,
            orderName: String,
            orderId: String,
        ) = PaymentEventJpaEntity()
            .apply {
                this.id = 0L
                this.buyerId = buyerId
                this.orderName = orderName
                this.orderId = orderId
            }
    }
}
