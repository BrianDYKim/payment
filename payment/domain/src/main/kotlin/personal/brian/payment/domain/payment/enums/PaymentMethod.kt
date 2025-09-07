package personal.brian.payment.domain.payment.enums

import personal.brian.payment.domain.error.ErrorCode
import personal.brian.payment.domain.error.PaymentDomainException

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
enum class PaymentMethod(val method: String) {
    EASY_PAY("간편결제"),
    ;

    companion object {
        fun get(method: String): PaymentMethod {
            return entries.find { it.method == method } ?: throw PaymentDomainException(
                "Payment Method (methpd: $method) 는 올바르이 않은 결제 방법입니다.",
                ErrorCode.METHOD_INVALID,
            )
        }
    }
}
