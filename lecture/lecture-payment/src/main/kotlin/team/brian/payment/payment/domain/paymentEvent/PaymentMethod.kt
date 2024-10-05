package team.brian.payment.payment.domain.paymentEvent

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
enum class PaymentMethod(val method: String) {
    EASY_PAY("간편결제"), ;

    companion object {
        fun get(method: String): PaymentMethod {
            return entries.find { it.method == method } ?: error("Payment Method (method: $method) 는 올바르지 않는 결제 방법입니다.")
        }
    }
}
