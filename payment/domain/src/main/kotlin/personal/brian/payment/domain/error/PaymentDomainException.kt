package personal.brian.payment.domain.error

import personal.brian.exception.exception.DomainException
import personal.brian.exception.severity.ExceptionSeverity

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
class PaymentDomainException(
    override val message: String,
    private val errorCode: ErrorCode,
) : DomainException(message, message, ExceptionSeverity.CRITICAL, errorCode.getErrorCode())
