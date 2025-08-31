package personal.brian.payment.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["personal.brian.payment.application", "personal.brian.payment.presentation"])
class PaymentApiApplication

fun main(args: Array<String>) {
    runApplication<PaymentApiApplication>(*args)
}
