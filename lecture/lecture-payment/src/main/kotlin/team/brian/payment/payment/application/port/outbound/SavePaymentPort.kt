package team.brian.payment.payment.application.port.outbound

import reactor.core.publisher.Mono
import team.brian.payment.payment.domain.paymentEvent.PaymentEvent

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
interface SavePaymentPort {
    fun save(paymentEvent: PaymentEvent): Mono<Void>
}
