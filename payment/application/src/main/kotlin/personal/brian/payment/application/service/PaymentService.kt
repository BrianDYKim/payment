package personal.brian.payment.application.service

import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import personal.brian.payment.application.operation.command.TossPaymentConfirmCommand
import personal.brian.payment.pspToss.executor.TossPaymentExecutor
import personal.brian.payment.pspToss.payload.PaymentConfirmPayload
import personal.brian.springExtension.logger.ApplicationLogger

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
@Service
class PaymentService(
    private val tossPaymentExecutor: TossPaymentExecutor,
) {
    private val logger by lazy { ApplicationLogger(this::class.java) }

    fun confirmTossPayment(command: TossPaymentConfirmCommand): String {
        val tossConfirmationPayload =
            PaymentConfirmPayload(
                paymentKey = command.paymentKey,
                orderId = command.orderId,
                amount = command.amount,
            )

        val confirmationResult =
            runBlocking {
                tossPaymentExecutor.execute(tossConfirmationPayload)
            }

        logger.info("Toss Payment Confirmation Result: $confirmationResult")

        return "OK!"
    }
}
