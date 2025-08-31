package personal.brian.extension.applicationEnvironment

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
enum class ApplicationEnvironment(val value: String) {
    LOCAL("local"),
    DEVELOPMENT("dev"),
    STAGE("st"),
    PRODUCTION("prod"),
    ;

    companion object {
        fun from(value: String): ApplicationEnvironment {
            return entries.find { it.value == value } ?: throw IllegalArgumentException("Invalid ApplicationEnvironment: $value")
        }
    }
}
