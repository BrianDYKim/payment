package personal.brian.payment.domain.helper

import java.util.UUID

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
object IdempotencyKeyGenerator {
    fun create(data: Any): String {
        return UUID.nameUUIDFromBytes(data.toString().toByteArray()).toString()
    }
}
