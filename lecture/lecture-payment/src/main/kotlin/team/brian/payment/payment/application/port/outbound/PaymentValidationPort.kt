package team.brian.payment.payment.application.port.outbound

import reactor.core.publisher.Mono

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
interface PaymentValidationPort {
    fun isValid(
        orderId: String,
        amount: Long,
    ): Mono<Boolean>
}
