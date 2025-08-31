package personal.brian.extension.pagination

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
data class Pagination<T>(
    val totalElements: Long,
    val page: Long,
    val perPage: Long,
    val elements: Int,
    val data: List<T>,
) {
    // 페이지네이션 내부의 데이터를 변환하는 메소드
    fun <U> transformData(transform: (T) -> U): Pagination<U> {
        return Pagination(
            totalElements = totalElements,
            page = page,
            perPage = perPage,
            elements = elements,
            data = data.map(transform),
        )
    }

    companion object {
        fun <T> of(
            totalElements: Long,
            page: Long,
            perPage: Long,
            data: List<T>,
        ): Pagination<T> {
            return Pagination(
                totalElements = totalElements,
                page = page,
                perPage = perPage,
                elements = data.size,
                data = data,
            )
        }
    }
}
