package team.brian.payment.payment.application.port.outbound

import reactor.core.publisher.Mono

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
interface PaymentStatusUpdatePort {
    fun updatePaymentStatusToExecuting(
        orderId: String,
        paymentKey: String,
    ): Mono<Boolean>
}
