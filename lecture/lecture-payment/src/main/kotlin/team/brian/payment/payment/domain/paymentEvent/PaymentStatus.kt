package team.brian.payment.payment.domain.paymentEvent

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
enum class PaymentStatus(description: String) {
    NOT_STARTED("결제 승인 시작 전"),
    EXECUTING("결제 승인 중"),
    SUCCESS("결제 승인 성공"),
    FAILURE("결제 승인 실패"),
    UNKNOWN("결제 승인 중 알수없는 에러"),
    ;

    companion object {
        fun get(status: String): PaymentStatus {
            return entries.find { it.name == status }
                ?: throw IllegalArgumentException("PaymentStatus: $status 는 올바르지 않는 결제 타입입니다.")
        }
    }
}
