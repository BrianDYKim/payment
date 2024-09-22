package team.brian.payment.payment.adapter.inbound.web.view

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono
import team.brian.payment.common.WebAdapter

/**
 * @author Doyeop Kim
 * @since 2024. 9. 22.
 */
@WebAdapter
@Controller
class CheckoutController {
    @GetMapping("/")
    fun checkoutPage(): Mono<String> {
        return Mono.just("checkout")
    }
}
