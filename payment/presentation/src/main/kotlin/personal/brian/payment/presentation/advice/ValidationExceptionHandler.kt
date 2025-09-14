package personal.brian.payment.presentation.advice

import jakarta.validation.ConstraintViolationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import personal.brian.extension.applicationEnvironment.ApplicationEnvironment
import personal.brian.response.generator.ApiResponseGenerator
import personal.brian.response.payload.FailureResponse
import personal.brian.springExtension.logger.ApplicationLogger

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
@RestControllerAdvice
@Order(1)
class ValidationExceptionHandler(
    @Value("\${appEnv}") private val appEnv: String,
) {
    private val logger by lazy { ApplicationLogger(this::class.java) }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(exception: ConstraintViolationException): FailureResponse.Response {
        val environment = ApplicationEnvironment.from(appEnv)

        if (environment == ApplicationEnvironment.LOCAL) {
            logger.warn(exception.message ?: "field error", exception)
        }

        val fieldErrorList =
            exception.constraintViolations.map { violation ->
                FailureResponse.FieldError.of(violation.propertyPath.toString(), violation.invalidValue.toString(), violation.message)
            }

        return ApiResponseGenerator.getValidationErrorResponse(fieldErrorList)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): FailureResponse.Response {
        val environment = ApplicationEnvironment.from(appEnv)

        if (environment == ApplicationEnvironment.LOCAL) {
            logger.warn(exception.message, exception)
        }

        val bindingResult = exception.bindingResult

        return ApiResponseGenerator.getValidationErrorResponse(bindingResult)
    }
}
