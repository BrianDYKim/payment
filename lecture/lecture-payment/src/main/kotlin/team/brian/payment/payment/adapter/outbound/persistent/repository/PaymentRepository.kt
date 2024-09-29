package team.brian.payment.payment.adapter.outbound.persistent.repository

import reactor.core.publisher.Mono
import team.brian.payment.payment.domain.paymentEvent.PaymentEvent

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
interface PaymentRepository {
    fun save(paymentEvent: PaymentEvent): Mono<Void>
}
