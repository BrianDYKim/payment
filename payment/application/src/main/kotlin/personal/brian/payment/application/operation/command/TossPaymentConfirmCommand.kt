package personal.brian.payment.application.operation.command

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import personal.brian.payment.application.dto.TossPaymentConfirmDto
import personal.brian.springExtension.validation.SelfValidating

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
data class TossPaymentConfirmCommand(
    @field:NotNull
    @field:NotEmpty
    val paymentKey: String,
    @field:NotNull
    @field:NotEmpty
    val orderId: String,
    @field:NotNull
    @field:NotEmpty
    val amount: String,
) : SelfValidating<TossPaymentConfirmCommand>() {
    init {
        validateSelf()
    }

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
