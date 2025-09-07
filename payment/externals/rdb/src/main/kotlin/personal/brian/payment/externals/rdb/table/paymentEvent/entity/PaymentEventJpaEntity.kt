package personal.brian.payment.externals.rdb.table.paymentEvent.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
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
    val id: Long,
    @Column(name = "buyer_id", nullable = false)
    val buyerId: Long,
    @Column(name = "is_payment_done", nullable = false)
    val isPaymentDone: Boolean,
    @Column(name = "payment_key", nullable = true)
    val paymentKey: String?,
    @Column(name = "order_id", nullable = true)
    val orderId: String?,
    @Column(name = "type", nullable = false)
    val type: String,
    @Column(name = "order_name", nullable = false)
    val orderName: String,
    @Column(name = "method", nullable = false)
    val method: String,
    @Column(name = "psp_raw_data", nullable = true)
    val pspRawData: String?,
    @Column(name = "approved_at", nullable = true)
    val approvedAt: LocalDateTime?,
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime,
)
