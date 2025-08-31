package personal.brian.payment.application.service

import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import personal.brian.payment.application.operation.command.TossPaymentConfirmCommand
import personal.brian.payment.pspToss.executor.TossPaymentExecutor
import personal.brian.payment.pspToss.payload.PaymentConfirmPayload
import personal.brian.payment.pspToss.payload.PaymentExecutionResult

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
@Service
class PaymentService(
    private val tossPaymentExecutor: TossPaymentExecutor,
) {
    fun confirmTossPayment(command: TossPaymentConfirmCommand): PaymentExecutionResult {
        val tossConfirmationPayload =
            PaymentConfirmPayload(
                paymentKey = command.paymentKey,
                orderId = command.orderId,
                amount = command.amount,
            )

        return runBlocking {
            tossPaymentExecutor.execute(tossConfirmationPayload)
        }
    }
}
