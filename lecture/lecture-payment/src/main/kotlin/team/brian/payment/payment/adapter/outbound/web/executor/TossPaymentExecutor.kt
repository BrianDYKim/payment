package team.brian.payment.payment.adapter.outbound.web.executor

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

/**
 * @author Doyeop Kim
 * @since 2024. 9. 22.
 */
@Component
class TossPaymentExecutor(
    @Qualifier("tossPaymentWebClient") private val tossPaymentWebClient: WebClient,
) {
    companion object {
        const val CONFIRM_URI = "/v1/payments/confirm"
    }

    fun execute(
        paymentKey: String,
        orderId: String,
        amount: Long,
    ): Mono<String> {
        return tossPaymentWebClient.post()
            .uri(CONFIRM_URI)
            .bodyValue(
                """
                {
                    "paymentKey": "$paymentKey",
                    "orderId": "$orderId",
                    "amount": $amount
                }
                """.trimIndent(),
            )
            .retrieve()
            .bodyToMono(String::class.java)
    }
}
