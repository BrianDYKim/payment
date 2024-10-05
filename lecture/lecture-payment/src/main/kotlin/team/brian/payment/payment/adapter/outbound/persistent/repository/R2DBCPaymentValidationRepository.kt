package team.brian.payment.payment.adapter.outbound.persistent.repository

import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import team.brian.payment.payment.adapter.outbound.persistent.exception.PaymentValidationException
import java.math.BigInteger

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
@Repository
class R2DBCPaymentValidationRepository(
    private val databaseClient: DatabaseClient,
) : PaymentValidationRepository {
    companion object {
        val SELECT_PAYMENT_TOTAL_AMOUNT_QUERY =
            """
            SELECT SUM(amount) as total_amount
            FROM payment_orders
            WHERE order_id = :orderId
            """.trimIndent()
    }

    override fun isValid(
        orderId: String,
        amount: Long,
    ): Mono<Boolean> {
        return databaseClient.sql(SELECT_PAYMENT_TOTAL_AMOUNT_QUERY)
            .bind("orderId", orderId)
            .fetch()
            .first()
            .handle { row, sink ->
                val isAmountConditionValid = (row["total_amount"] as BigInteger).toLong() == amount

                when (isAmountConditionValid) {
                    true -> sink.next(true)
                    false -> sink.error(PaymentValidationException("결제 (orderId: $orderId) 에서 금액 (amount: $amount)이 올바르지 않습니다."))
                }
            }
    }
}
