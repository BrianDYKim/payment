package team.brian.payment.payment.test

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.reactive.TransactionalOperator

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
@TestConfiguration
class PaymentTestConfiguration {
    @Bean
    fun paymentDatabaseHelper(
        databaseClient: DatabaseClient,
        transactionalOperator: TransactionalOperator,
    ): PaymentDatabaseHelper {
        return R2DBCPaymentDatabaseHelper(databaseClient, transactionalOperator)
    }
}
