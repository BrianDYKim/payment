package team.brian.payment.payment.adapter.inbound.web.response

import org.springframework.http.HttpStatus

/**
 * @author Doyeop Kim
 * @since 2024. 9. 22.
 */
data class ApiResponse<T>(
    val status: Int = 200,
    val message: String = "",
    val data: T? = null,
) {
    companion object {
        fun <T> with(
            status: HttpStatus,
            message: String,
            data: T?,
        ): ApiResponse<T> {
            return ApiResponse(
                status = status.value(),
                message = message,
                data = data,
            )
        }
    }
}
