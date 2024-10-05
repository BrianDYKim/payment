package team.brian.payment.payment.adapter.outbound.persistent.repository

import reactor.core.publisher.Mono

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
interface PaymentStatusUpdateRepository {
    fun updatePaymentStatusToExecuting(
        orderId: String,
        paymentKey: String,
    ): Mono<Boolean>
}
