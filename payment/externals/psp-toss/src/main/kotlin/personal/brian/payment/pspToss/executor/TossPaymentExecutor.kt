package personal.brian.payment.pspToss.executor

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import personal.brian.payment.pspToss.payload.PaymentConfirmPayload
import personal.brian.payment.pspToss.payload.PaymentExecutionResult

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Component
class TossPaymentExecutor(
    @Qualifier("tossPaymentRestClient") private val tossPaymentRestClient: RestClient,
) {
    companion object {
        private const val PAYMENT_CONFIRMATION_URI = "/v1/payments/confirm"
    }

    suspend fun execute(payload: PaymentConfirmPayload): PaymentExecutionResult? {
        tossPaymentRestClient.post()
            .uri(PAYMENT_CONFIRMATION_URI)
            .header("Idempotency-Key", payload.orderId)
            .body(
                """
                {
                  "paymentKey": "${payload.paymentKey}",
                  "orderId": "${payload.orderId}", 
                  "amount": ${payload.amount}
                }
                """.trimIndent(),
            )
            .retrieve()

        return null
    }
}
