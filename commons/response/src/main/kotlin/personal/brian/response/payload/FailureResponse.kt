package personal.brian.response.payload

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.BindingResult

sealed class FailureResponse {
    /**
     * Error에 대한 Response를 담당하는 data class
     * @param errorCode: 에러코드 (도메인 코드 3자리 + 상세 코드 3자리 + HTTP 상태 코드 3자리)
     * @param errorMessage: 에러 메시지
     * @param result: 응답 결과 (기본값은 실패)
     */
    @Schema(name = "FailureResponse.Response")
    data class Response(
        val errorCode: String,
        val errorMessage: String,
        @field:Schema(example = "false")
        val result: Boolean = false,
    ) {
        companion object {
            /**
             * Validation 과정에서 발생한 오류의 에러코드
             * FLD + 000 + 400 = Field 에러, 000번 에러, 에러 응답은 400
             */
            private val VALIDATION_ERROR_CODE = "FLD000400"

            /**
             * 알 수 없는 에러가 발생한 경우의 에러코드
             * NAK + 000 + 500 = Not Available Knowledge, 000번 에러, 에러 응답은 500
             */
            private val UNKNOWN_ERROR_CODE = "NAK000500"

            private val UNKNOWN_ERROR_MESSAGE = "unknown"

            // 알 수 없는 에러가 발생한 경우
            fun withUnknownError(): Response = Response(UNKNOWN_ERROR_CODE, UNKNOWN_ERROR_MESSAGE)

            // 필드에서 에러가 발생한게 아니라 비지니스 로직 상에서 에러가 발생한 경우
            fun withBusinessError(
                errorCode: String,
                errorMessage: String,
            ): Response = Response(errorCode, errorMessage)

            // Validation 과정에서 발생한 오류지만, 단 하나의 필드에서만 오류가 발생하는 경우
            fun withValidationError(fieldError: FieldError): Response = Response(VALIDATION_ERROR_CODE, fieldError.message)

            // Validation 과정에서 발생한 오류지만, 여러개의 필드에서 오류가 발생하는 경우 -> 단 하나의 필드에 대해서만 에러를 내려준다
            fun withValidationError(fieldErrorList: List<FieldError>): Response =
                with(fieldErrorList) {
                    Response(VALIDATION_ERROR_CODE, this.first().message)
                }

            // Validation 과정에서 발생한 오류지만, BindingResult로 에러가 전달되는 경우
            fun withValidationError(bindingResult: BindingResult): Response =
                with(bindingResult) {
                    val message = FieldError.of(this).first().message

                    return@with Response(VALIDATION_ERROR_CODE, message)
                }
        }
    }

    /**
     * Field에 대한 에러를 담아주는 inner class
     * @param field 에러가 발생한 필드의 이름
     * @param value 에러가 발생한 필드가 원래 가지고 있던 값
     * @param message 에러가 발생한 이유
     */
    class FieldError private constructor(val field: String, val value: String, val message: String) {
        companion object {
            // 에러를 일으키는 필드가 단 하나만 존재하는 경우
            fun of(
                field: String,
                value: String,
                message: String,
            ): FieldError = FieldError(field, value, message)

            // BindingResult로 에러가 전달되는 경우
            fun of(bindingResult: BindingResult): List<FieldError> =
                with(bindingResult) {
                    this.fieldErrors.map { of(it.field, it.rejectedValue.toString(), it.defaultMessage!!) }
                }
        }
    }
}
