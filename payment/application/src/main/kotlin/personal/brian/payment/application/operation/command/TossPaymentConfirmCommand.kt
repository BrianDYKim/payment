package personal.brian.payment.application.operation.command

import personal.brian.payment.application.dto.TossPaymentConfirmDto

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
data class TossPaymentConfirmCommand(
    val paymentKey: String,
    val orderId: String,
    val amount: String,
) {
    companion object {
        fun from(request: TossPaymentConfirmDto.Request): TossPaymentConfirmCommand {
            return TossPaymentConfirmCommand(
                paymentKey = request.paymentKey,
                orderId = request.orderId,
                amount = request.amount,
            )
        }
    }
}
