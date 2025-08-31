package personal.brian.payment.presentation.advice

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import personal.brian.response.generator.ApiResponseGenerator
import personal.brian.response.payload.FailureResponse
import personal.brian.springExtension.logger.ApplicationLogger

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class GlobalExceptionHandler {
    private val logger by lazy { ApplicationLogger(this::class.java) }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGlobalException(exception: Exception): FailureResponse.Response {
        // Logging
        logger.error(exception.message, exception)

        return ApiResponseGenerator.getUnknownErrorResponse()
    }
}
