package personal.brian.payment.presentation.advice

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException
import personal.brian.response.generator.ApiResponseGenerator
import personal.brian.response.payload.FailureResponse

@RestControllerAdvice
@Order(2)
class NoResourceFoundExceptionHandler {
    @ExceptionHandler(NoResourceFoundException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleNoResourceFoundException(exception: NoResourceFoundException): FailureResponse.Response {
        return ApiResponseGenerator.getUnknownErrorResponse()
    }
}
