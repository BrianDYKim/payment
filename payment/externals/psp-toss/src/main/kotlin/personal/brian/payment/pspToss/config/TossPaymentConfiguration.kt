package personal.brian.payment.pspToss.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestClient
import java.util.Base64

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Configuration
class TossPaymentConfiguration(
    @Value("\${PSP.toss.url}") private val baseUrl: String,
    @Value("\${PSP.toss.secretKey}") private val secretKey: String,
) {
    @Bean(name = ["tossPaymentRestClient"])
    fun tossPaymentRestClient(): RestClient {
        val encodedSecretKey = Base64.getEncoder().encodeToString((secretKey + ":").toByteArray())

        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic $encodedSecretKey")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build()
    }
}
