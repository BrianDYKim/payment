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
    var id: Long = 0L,
    @Column(name = "payment_order_id", nullable = false)
    var paymentOrderId: Long = 0L,
    @Column(name = "previous_status", nullable = true)
    var previousStatus: String? = null,
    @Column(name = "new_status", nullable = true)
    var newStatus: String? = null,
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "changed_by", nullable = true)
    var changedBy: String? = null,
    @Column(name = "reason", nullable = true)
    var reason: String? = null,
)
