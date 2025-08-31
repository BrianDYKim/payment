package personal.brian.payment.pspToss.executor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import personal.brian.payment.domain.PSPConfirmationStatus
import personal.brian.payment.domain.PaymentFailure
import personal.brian.payment.domain.PaymentMethod
import personal.brian.payment.domain.PaymentType
import personal.brian.payment.pspToss.exception.PSPConfirmationException
import personal.brian.payment.pspToss.exception.TossPaymentError
import personal.brian.payment.pspToss.payload.PaymentConfirmPayload
import personal.brian.payment.pspToss.payload.PaymentExecutionResult
import personal.brian.payment.pspToss.payload.PaymentExtraDetails
import personal.brian.payment.pspToss.response.TossFailureResponse
import personal.brian.payment.pspToss.response.TossPaymentConfirmationResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Component
class TossPaymentExecutor(
    @Qualifier("tossPaymentRestClient") private val tossPaymentRestClient: RestClient,
    private val objectMapper: ObjectMapper,
) {
    companion object {
        private const val PAYMENT_CONFIRMATION_URI = "/v1/payments/confirm"
    }

    suspend fun execute(payload: PaymentConfirmPayload): PaymentExecutionResult {
        try {
            val response =
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
                    .onStatus(
                        { statusCode: HttpStatusCode -> statusCode.is4xxClientError || statusCode.is5xxServerError },
                    ) { request, clientResponse ->
                        val failureResponse = objectMapper.readValue(clientResponse.body, TossFailureResponse::class.java)
                        val error = TossPaymentError.get(failureResponse.code)
                        throw PSPConfirmationException(
                            errorCode = error.name,
                            errorMessage = error.description,
                            isSuccess = error.isSuccess(),
                            isFailure = error.isFailure(),
                            isUnknown = error.isUnknown(),
                            isRetryableError = error.isRetryableError(),
                        )
                    }
                    .body<TossPaymentConfirmationResponse>()

            return PaymentExecutionResult(
                paymentKey = payload.paymentKey,
                orderId = payload.orderId,
                extraDetails =
                    PaymentExtraDetails(
                        type = PaymentType.get(response!!.type),
                        method = PaymentMethod.get(response.method),
                        approvedAt = LocalDateTime.parse(response.approvedAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                        orderName = response.orderName,
                        pspConfirmationStatus = PSPConfirmationStatus.get(response.status),
                        totalAmount = response.totalAmount.toLong(),
                        pspRawData = response.toString(),
                    ),
                isSuccess = true,
                isFailure = false,
                isUnknown = false,
                isRetryable = false,
            )
        } catch (e: PSPConfirmationException) {
            return PaymentExecutionResult(
                paymentKey = payload.paymentKey,
                orderId = payload.orderId,
                failure =
                    PaymentFailure(
                        errorCode = e.errorCode,
                        message = e.errorMessage,
                    ),
                isSuccess = e.isSuccess,
                isFailure = e.isFailure,
                isUnknown = e.isUnknown,
                isRetryable = e.isRetryableError,
            )
        } catch (e: Exception) {
            return PaymentExecutionResult(
                paymentKey = payload.paymentKey,
                orderId = payload.orderId,
                failure =
                    PaymentFailure(
                        errorCode = "UNKNOWN",
                        message = e.message ?: "알 수 없는 오류가 발생했습니다.",
                    ),
                isSuccess = false,
                isFailure = false,
                isUnknown = true,
                isRetryable = true,
            )
        }
    }
}
