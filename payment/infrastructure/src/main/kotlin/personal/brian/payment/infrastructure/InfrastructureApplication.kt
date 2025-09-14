package personal.brian.payment.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @author Doyeop Kim
 * @since 2025. 9. 14.
 */
@SpringBootApplication
@ComponentScan(basePackages = ["personal.brian.payment.infrastructure", "personal.brian.payment.externals.rdb"])
class InfrastructureApplication
