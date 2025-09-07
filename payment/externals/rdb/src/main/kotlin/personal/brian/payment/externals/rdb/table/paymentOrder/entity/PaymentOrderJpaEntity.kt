package personal.brian.payment.externals.rdb.table.paymentOrder.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@Entity
@Table(name = "payment_orders")
class PaymentOrderJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "payment_event_id", nullable = false)
    val paymentEventId: Long,
    @Column(name = "seller_id", nullable = false)
    val sellerId: Long,
    @Column(name = "product_id", nullable = false)
    val productId: Long,
    @Column(name = "order_id", nullable = false)
    val orderId: String,
    @Column(name = "amount", nullable = false)
    val amount: BigDecimal,
    @Column(name = "payment_order_status", nullable = false)
    val paymentOrderStatus: String,
    @Column(name = "ledger_updated", nullable = false)
    val ledgerUpdated: Boolean,
    @Column(name = "wallet_updated", nullable = false)
    val walletUpdated: Boolean,
    @Column(name = "failed_count", nullable = false)
    val failedCount: Byte,
    @Column(name = "threshold", nullable = false)
    val threshold: Byte,
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime,
)
