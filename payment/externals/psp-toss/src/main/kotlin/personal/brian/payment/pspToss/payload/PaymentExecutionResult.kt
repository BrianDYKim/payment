package personal.brian.payment.pspToss.payload

import personal.brian.payment.domain.payment.enums.PSPConfirmationStatus
import personal.brian.payment.domain.payment.enums.PaymentMethod
import personal.brian.payment.domain.payment.enums.PaymentStatus
import personal.brian.payment.domain.payment.enums.PaymentType
import personal.brian.payment.domain.payment.payload.PaymentFailure
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
data class PaymentExecutionResult(
    val paymentKey: String,
    val orderId: String,
    val extraDetails: PaymentExtraDetails? = null,
    val failure: PaymentFailure? = null,
    val isSuccess: Boolean,
    val isFailure: Boolean,
    val isUnknown: Boolean,
    val isRetryable: Boolean,
) {
    fun paymentStatus(): PaymentStatus {
        return when {
            isSuccess -> PaymentStatus.SUCCESS
            isFailure -> PaymentStatus.FAILURE
            isUnknown -> PaymentStatus.UNKNOWN
            else -> error("결제 (orderId: $orderId) 는 올바르지 않은 결제 상태입니다.")
        }
    }

    init {
        require(isSuccess || isFailure || isUnknown) {
            "결제 (orderId: $orderId) 는 올바르지 않은 결제 상태입니다."
        }
    }
}

data class PaymentExtraDetails(
    val type: PaymentType,
    val method: PaymentMethod,
    val approvedAt: LocalDateTime,
    val orderName: String,
    val pspConfirmationStatus: PSPConfirmationStatus,
    val totalAmount: Long,
    val pspRawData: String,
)
