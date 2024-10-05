package team.brian.payment.payment.domain.paymentExecution

import team.brian.payment.payment.domain.paymentConfirmation.PSPConfirmationStatus
import team.brian.payment.payment.domain.paymentEvent.PaymentMethod
import team.brian.payment.payment.domain.paymentEvent.PaymentType
import java.time.LocalDateTime

/**
 * @author Brian
 * @since 2024. 10. 5.
 */
data class PaymentExecutionResult(
    val paymentKey: String,
    val orderId: String,
    val extraDetails: PaymentExtraDetails? = null,
    val failure: Failure? = null,
    val isSuccess: Boolean,
    val isFailure: Boolean,
    val isUnknown: Boolean,
    val isRetryable: Boolean,
) {
    init {
        require(listOf(isSuccess, isFailure, isUnknown).count { it } == 1) {
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

data class Failure(
    val errorCode: String,
    val message: String,
)
