package personal.brian.springExtension.annotation

import org.springframework.stereotype.Component

/**
 * @author Doyeop Kim
 * @since 2025. 8. 31.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class UseCase
