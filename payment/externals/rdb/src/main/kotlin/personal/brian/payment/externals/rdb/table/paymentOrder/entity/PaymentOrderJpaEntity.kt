package personal.brian.payment.externals.rdb.table.paymentOrder.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import personal.brian.payment.domain.payment.enums.PaymentStatus
import personal.brian.payment.domain.price.Currency
import personal.brian.payment.domain.price.Price
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
    var id: Long = 0L,
    @Column(name = "payment_event_id", nullable = false)
    var paymentEventId: Long = 0L,
    @Column(name = "seller_id", nullable = false)
    var sellerId: Long = 0L,
    @Column(name = "product_id", nullable = false)
    var productId: Long = 0L,
    @Column(name = "order_id", nullable = false)
    var orderId: String = "",
    @Column(name = "amount", nullable = false)
    var amount: BigDecimal = BigDecimal.ZERO,
    @Column(name = "currency", nullable = false)
    var currency: String = Currency.KRW.name,
    @Column(name = "payment_order_status", nullable = false)
    var paymentOrderStatus: String = PaymentStatus.NOT_STARTED.name,
    @Column(name = "ledger_updated", nullable = false)
    var ledgerUpdated: Boolean = false,
    @Column(name = "wallet_updated", nullable = false)
    var walletUpdated: Boolean = false,
    @Column(name = "failed_count", nullable = false)
    var failedCount: Int = 0,
    @Column(name = "threshold", nullable = false)
    var threshold: Int = 0,
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun new(
            paymentEventId: Long,
            sellerId: Long,
            productId: Long,
            orderId: String,
            price: Price,
            status: PaymentStatus,
        ) = PaymentOrderJpaEntity().apply {
            this.id = 0L
            this.paymentEventId = paymentEventId
            this.sellerId = sellerId
            this.productId = productId
            this.orderId = orderId
            this.amount = price.amount
            this.currency = price.currency.name
            this.paymentOrderStatus = status.name
        }
    }
}
