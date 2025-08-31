package personal.brian.response.payload

import io.swagger.v3.oas.annotations.media.Schema
import kotlin.math.max

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
sealed class SuccessResponse {
    // 단일 데이터에 대한 성공 응답 정의
    @Schema(name = "SuccessResponse.Single")
    data class Single<T>(
        val data: T,
        @field:Schema(example = "2000")
        val result: Boolean = true,
    )

    // Null의 가능성이 있는 단일 데이터에 대한 성공 응답 정의
    @Schema(name = "SuccessResponse.SingleNullable")
    data class SingleNullable<T>(
        val data: T?,
        val result: Boolean = true,
    )

    // 복수개의 데이터에 대한 성공 응답 정의
    @Schema(name = "SuccessResponse.Multiple")
    data class Multiple<T>(
        val data: List<T>,
        val result: Boolean = true,
    )

    // 페이지네이션 된 성공 응답 정의
    @Schema(name = "SuccessResponse.Paginated")
    data class Paginated<T>(
        val data: List<T>,
        val meta: PaginationMeta,
        val links: PaginationLinks,
        val result: Boolean = true,
    ) {
        companion object {
            fun <T> of(
                totalPages: Long,
                totalElements: Long,
                page: Long,
                baseUrl: String,
                data: List<T>,
                extraParams: Map<String, String> = emptyMap(),
            ): Paginated<T> {
                val paginationMeta = PaginationMeta(totalPages, totalElements, page, data.size)
                val paginationLinks = PaginationLinks.of(baseUrl, page, totalPages, extraParams)

                return Paginated(data, paginationMeta, paginationLinks)
            }
        }
    }

    @Schema(name = "SuccessResponse.PaginationMeta")
    class PaginationMeta(
        @field:Schema(example = "10")
        val totalPages: Long,
        @field:Schema(example = "100")
        val totalElements: Long,
        @field:Schema(example = "1")
        val page: Long,
        @field:Schema(example = "10")
        val elements: Int,
    )

    @Schema(name = "SuccessResponse.PaginationLinks")
    class PaginationLinks(
        @field:Schema(example = "http://localhost:8080/api/v1/payments?page=2&size=10")
        val current: String,
        @field:Schema(example = "http://localhost:8080/api/v1/payments?page=1&size=10")
        val first: String,
        @field:Schema(example = "http://localhost:8080/api/v1/payments?page=10&size=10")
        val last: String,
        @field:Schema(example = "http://localhost:8080/api/v1/payments?page=1&size=10")
        val prev: String?,
        @field:Schema(example = "http://localhost:8080/api/v1/payments?page=3&size=10")
        val next: String?,
    ) {
        companion object {
            fun of(
                baseUrl: String,
                page: Long,
                totalPages: Long,
                extraParams: Map<String, String> = emptyMap(),
            ): PaginationLinks {
                fun makeUrl(targetPage: Long): String {
                    val params = extraParams.toMutableMap()
                    params["page"] = targetPage.toString()
                    val query = params.entries.joinToString("&") { "${it.key}=${it.value}" }
                    return "$baseUrl?$query"
                }

                val effectiveTotalPages = max(1L, totalPages)
                return PaginationLinks(
                    current = makeUrl(page),
                    first = makeUrl(1),
                    last = makeUrl(effectiveTotalPages),
                    prev = if (page > 1) makeUrl(page - 1) else null,
                    next = if (page < effectiveTotalPages) makeUrl(page + 1) else null,
                )
            }
        }
    }
}
