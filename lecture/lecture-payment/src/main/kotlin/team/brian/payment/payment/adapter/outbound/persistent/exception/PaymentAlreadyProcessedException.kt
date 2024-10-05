package team.brian.payment.payment.adapter.outbound.persistent.exception

import team.brian.payment.payment.domain.paymentEvent.PaymentStatus

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
class PaymentAlreadyProcessedException(
    val status: PaymentStatus,
    message: String,
) : RuntimeException(message)
