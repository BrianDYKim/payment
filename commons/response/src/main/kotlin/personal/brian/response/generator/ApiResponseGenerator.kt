package personal.brian.response.generator

import org.springframework.validation.BindingResult
import personal.brian.extension.pagination.Pagination
import personal.brian.response.payload.FailureResponse
import personal.brian.response.payload.SuccessResponse
import kotlin.math.ceil

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
object ApiResponseGenerator {
    // 성공에 대한 결과를 리턴하는 메소드
    fun getSuccessResponse(): SuccessResponse.Single<String> = SuccessResponse.Single("OK")

    // 단일 데이터를 가지는 성공에 대한 결과를 리턴하는 메소드
    fun <T> getSingleDataResponse(item: T): SuccessResponse.Single<T> = SuccessResponse.Single(item)

    // Null의 가능성이 있는 단일 데이터를 가지는 성공에 대한 결과를 리턴하는 메소드
    fun <T> getSingleNullableDataResponse(item: T?): SuccessResponse.SingleNullable<T> = SuccessResponse.SingleNullable(item)

    // 여러개의 데이터를 가지는 성공에 대한 결과를 리턴하는 메소드
    fun <T> getMultipleDataResponse(items: List<T>): SuccessResponse.Multiple<T> = SuccessResponse.Multiple(items)

    // 페이지네이션 된 데이터를 가지는 성공에 대한 결과를 리턴하는 메소드
    fun <T> getPaginatedDataResponse(
        baseUrl: String,
        totalElements: Long,
        page: Long,
        elements: Long,
        items: List<T>,
        extraParams: Map<String, String>,
    ): SuccessResponse.Paginated<T> {
        val totalPages = ceil(totalElements.toDouble() / elements.toDouble()).toLong()

        return SuccessResponse.Paginated.of(totalPages, totalElements, page, baseUrl, items, extraParams)
    }

    // 페이지네이션 된 데이터를 가지는 성공에 대한 결과를 리턴하는 메소드
    fun <T> getPaginatedDataResponse(
        paginationResult: Pagination<T>,
        baseUrl: String,
        extraParams: Map<String, String>,
    ): SuccessResponse.Paginated<T> {
        val totalPages = ceil(paginationResult.totalElements.toDouble() / paginationResult.perPage.toDouble()).toLong()

        return SuccessResponse.Paginated.of(
            totalPages = totalPages,
            totalElements = paginationResult.totalElements,
            page = paginationResult.page,
            baseUrl = baseUrl,
            data = paginationResult.data,
            extraParams = extraParams,
        )
    }

    // 알 수 없는 에러가 발생한 경우 결과를 리턴하는 메소드
    fun getUnknownErrorResponse(): FailureResponse.Response = FailureResponse.Response.withUnknownError()

    // 비지니스 상에서 에러가 발생한 경우 결과를 리턴하는 메소드
    fun getBusinessErrorResponse(
        errorCode: String,
        errorMessage: String,
    ): FailureResponse.Response = FailureResponse.Response.withBusinessError(errorCode, errorMessage)

    // Validation 과정에서 발생한 오류지만, 단 하나의 필드에서만 오류가 발생하는 경우 결과를 리턴하는 메소드
    fun getValidationErrorResponse(fieldErrorList: List<FailureResponse.FieldError>): FailureResponse.Response =
        FailureResponse.Response.withValidationError(fieldErrorList)

    // field 검증 과정에서 에러가 발생하였으며, 결과가 bindingResult로 전달되는 경우
    fun getValidationErrorResponse(bindingResult: BindingResult): FailureResponse.Response =
        FailureResponse.Response.withValidationError(bindingResult)
}
