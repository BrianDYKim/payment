package team.brian.payment.payment.adapter.outbound.web.toss.executor

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import team.brian.payment.payment.adapter.outbound.web.toss.response.TossPaymentConfirmationResponse
import team.brian.payment.payment.application.port.inbound.PaymentConfirmCommand
import team.brian.payment.payment.domain.paymentConfirmation.PSPConfirmationStatus
import team.brian.payment.payment.domain.paymentEvent.PaymentMethod
import team.brian.payment.payment.domain.paymentEvent.PaymentType
import team.brian.payment.payment.domain.paymentExecution.PaymentExecutionResult
import team.brian.payment.payment.domain.paymentExecution.PaymentExtraDetails
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author Doyeop Kim
 * @since 2024. 9. 22.
 */
@Component
class TossPaymentExecutor(
    @Qualifier("tossPaymentWebClient") private val tossPaymentWebClient: WebClient,
) : PaymentExecutor {
    companion object {
        const val CONFIRM_URI = "/v1/payments/confirm"
    }

    override fun execute(command: PaymentConfirmCommand): Mono<PaymentExecutionResult> {
        return tossPaymentWebClient.post()
            .uri(CONFIRM_URI)
            .header("Idempotency-Key", command.orderId)
            .bodyValue(
                """
                {
                    "paymentKey": "${command.paymentKey}",
                    "orderId": "${command.orderId}",
                    "amount": ${command.amount}
                }
                """.trimIndent(),
            )
            .retrieve()
            .bodyToMono(TossPaymentConfirmationResponse::class.java)
            .map {
                PaymentExecutionResult(
                    paymentKey = command.paymentKey,
                    orderId = command.orderId,
                    extraDetails =
                        PaymentExtraDetails(
                            type = PaymentType.get(it.type),
                            method = PaymentMethod.get(it.method),
                            approvedAt = LocalDateTime.parse(it.approvedAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                            pspRawData = it.toString(),
                            orderName = it.orderName,
                            pspConfirmationStatus = PSPConfirmationStatus.get(it.status),
                            totalAmount = it.totalAmount.toLong(),
                        ),
                    isSuccess = true,
                    isFailure = false,
                    isUnknown = false,
                    isRetryable = false,
                )
            }
    }
}
