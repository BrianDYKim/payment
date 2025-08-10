package personal.brian.coroutineExtension.factory

import kotlinx.coroutines.CoroutineDispatcher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import personal.brian.coroutineExtension.scope.ApplicationCoroutineScope

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Component
class ApplicationCoroutineDispatcherFactory(
    @Qualifier("applicationIODispatcher") val _applicationIODispatcher: CoroutineDispatcher,
) {
    init {
        ioDispatcher = _applicationIODispatcher
    }

    companion object {
        private lateinit var ioDispatcher: CoroutineDispatcher

        fun getInstance(scope: ApplicationCoroutineScope): CoroutineDispatcher =
            when (scope) {
                ApplicationCoroutineScope.IO -> ioDispatcher
            }
    }
}
