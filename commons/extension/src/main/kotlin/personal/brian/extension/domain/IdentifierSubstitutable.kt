package personal.brian.extension.domain

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
interface IdentifierSubstitutable<T, ID> {
    val id: ID

    fun substituteIdentifier(id: ID): T
}
