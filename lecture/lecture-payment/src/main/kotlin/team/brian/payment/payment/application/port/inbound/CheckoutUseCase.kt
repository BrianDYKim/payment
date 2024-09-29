package team.brian.payment.payment.application.port.inbound

import reactor.core.publisher.Mono
import team.brian.payment.payment.domain.checkoutResult.CheckoutResult

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
interface CheckoutUseCase {
    fun checkout(command: CheckoutCommand): Mono<CheckoutResult>
}
