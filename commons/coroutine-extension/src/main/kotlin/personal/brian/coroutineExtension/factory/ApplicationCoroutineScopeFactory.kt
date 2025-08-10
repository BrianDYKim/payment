package personal.brian.coroutineExtension.factory

import kotlinx.coroutines.CoroutineScope
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import personal.brian.coroutineExtension.scope.ApplicationCoroutineScope

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Component
class ApplicationCoroutineScopeFactory(
    @Qualifier("applicationCoroutineIOScope") val _applicationCoroutineIOScope: CoroutineScope,
) {
    init {
        ioScope = _applicationCoroutineIOScope
    }

    companion object {
        private lateinit var ioScope: CoroutineScope

        fun getInstance(scope: ApplicationCoroutineScope): CoroutineScope =
            when (scope) {
                ApplicationCoroutineScope.IO -> ioScope
            }
    }
}
