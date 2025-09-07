package personal.brian.payment.externals.rdb.table.paymentOrderHistory.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@Entity
@Table(name = "payment_order_histories")
class PaymentOrderHistoryJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "payment_order_id", nullable = false)
    val paymentOrderId: Long,
    @Column(name = "previous_status", nullable = true)
    val previousStatus: String?,
    @Column(name = "new_status", nullable = true)
    val newStatus: String?,
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
    @Column(name = "changed_by", nullable = true)
    val changedBy: String?,
    @Column(name = "reason", nullable = true)
    val reason: String?,
)
