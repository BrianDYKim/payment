package personal.brian.payment.domain.error

import org.springframework.http.HttpStatus

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
enum class ErrorCode(
    private val detailCode: String,
    private val status: HttpStatus = HttpStatus.BAD_REQUEST,
) {
    METHOD_INVALID("001"),
    STATUS_INVALID("002"),
    TYPE_INVALID("003"),
    CONFIRMATION_STATUS_INVALID("004"),
    CURRENCY_INVALID("005"),
    PAYMENT_ORDER_NOT_FOUND("006"),
    ;

    fun getErrorCode(): String {
        return "PAD" + detailCode + status.value().toString()
    }
}
