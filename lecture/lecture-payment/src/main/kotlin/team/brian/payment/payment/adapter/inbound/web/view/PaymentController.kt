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
class PaymentController {
    @GetMapping("/success")
    fun successPage(): Mono<String> {
        return Mono.just("success")
    }

    @GetMapping("/fail")
    fun failPage(): Mono<String> {
        return Mono.just("fail")
    }
}
