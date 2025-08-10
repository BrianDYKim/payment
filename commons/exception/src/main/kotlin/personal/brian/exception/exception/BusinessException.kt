package personal.brian.exception.exception

import personal.brian.exception.severity.ExceptionSeverity

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
abstract class BusinessException(
    override val message: String,
    open val internalMessage: String,
    open val severity: ExceptionSeverity,
    open val errorCodeString: String,
) : RuntimeException(message)
