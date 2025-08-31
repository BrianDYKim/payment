package personal.brian.payment.presentation.advice

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import personal.brian.exception.exception.BusinessException
import personal.brian.exception.severity.ExceptionSeverity
import personal.brian.extension.applicationEnvironment.ApplicationEnvironment
import personal.brian.response.generator.ApiResponseGenerator
import personal.brian.response.payload.FailureResponse
import personal.brian.springExtension.logger.ApplicationLogger

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
@RestControllerAdvice
@Order(0)
class BusinessExceptionHandler(
    @Value("\${appEnv}") private val appEnv: String,
) {
    private val logger by lazy { ApplicationLogger(this::class.java) }

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTutoringBusinessException(
        exception: BusinessException,
        request: HttpServletRequest,
    ): FailureResponse.Response {
        val environment = ApplicationEnvironment.from(appEnv)

        // Logging
        if (environment == ApplicationEnvironment.LOCAL) {
            when (exception.severity) {
                ExceptionSeverity.LOW -> logger.warn(exception.internalMessage, exception)
                ExceptionSeverity.MEDIUM -> logger.warn(exception.internalMessage, exception)
                ExceptionSeverity.HIGH -> logger.error(exception.internalMessage, exception)
                ExceptionSeverity.CRITICAL -> logger.error(exception.internalMessage, exception)
            }
        } else {
            when (exception.severity) {
                ExceptionSeverity.LOW -> logger.warn(exception.internalMessage)
                ExceptionSeverity.MEDIUM -> logger.warn(exception.internalMessage, exception)
                ExceptionSeverity.HIGH -> logger.error(exception.internalMessage, exception)
                ExceptionSeverity.CRITICAL -> logger.error(exception.internalMessage, exception)
            }
        }

        return ApiResponseGenerator.getBusinessErrorResponse(exception.errorCodeString, exception.message)
    }
}
