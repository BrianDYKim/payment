package personal.brian.payment.domain.payment.enums

import personal.brian.payment.domain.error.ErrorCode
import personal.brian.payment.domain.error.PaymentDomainException

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
enum class PaymentType(description: String) {
    NORMAL("일반 결제"),
    ;

    companion object {
        fun get(type: String): PaymentType {
            return entries.find { it.name == type } ?: throw PaymentDomainException(
                "PaymentType (type: $type) 은 올바르지 않은 결제 타입입니다.",
                ErrorCode.TYPE_INVALID,
            )
        }
    }
}
