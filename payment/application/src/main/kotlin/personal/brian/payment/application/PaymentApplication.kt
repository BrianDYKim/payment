package personal.brian.payment.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
@SpringBootApplication
@ComponentScan(basePackages = ["personal.brian.payment.pspToss", "personal.brian.payment.infrastructure"])
class PaymentApplication
