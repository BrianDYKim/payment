package personal.brian.springExtension.annotation

import org.springframework.stereotype.Repository

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Repository
annotation class PersistenceAdapter
