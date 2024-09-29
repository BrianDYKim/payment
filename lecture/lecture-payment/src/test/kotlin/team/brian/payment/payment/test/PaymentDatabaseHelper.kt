package team.brian.payment.payment.test

import reactor.core.publisher.Mono
import team.brian.payment.payment.domain.paymentEvent.PaymentEvent

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
interface PaymentDatabaseHelper {
    fun getPayments(orderId: String): PaymentEvent?

    fun clean(): Mono<Void>
}
