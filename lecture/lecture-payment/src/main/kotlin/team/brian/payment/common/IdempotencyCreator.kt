package team.brian.payment.common

import java.util.UUID

/**
 * @author Doyeop Kim
 * @since 2024. 9. 29.
 */
object IdempotencyCreator {
    fun create(data: Any): String {
        return UUID.nameUUIDFromBytes(data.toString().toByteArray()).toString()
    }
}
