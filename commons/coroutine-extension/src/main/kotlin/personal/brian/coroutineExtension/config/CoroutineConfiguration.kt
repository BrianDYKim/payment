package personal.brian.coroutineExtension.config

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.util.concurrent.Executor

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Configuration
class CoroutineConfiguration {
    @Bean(name = ["applicationIODispatcher"])
    @Primary
    fun applicationIODispatcher(
        @Qualifier("coroutineExecutor") executor: Executor,
    ): CoroutineDispatcher {
        return executor.asCoroutineDispatcher()
    }

    @Bean(name = ["applicationCoroutineIOScope"])
    @Primary
    fun applicationCoroutineIOScope(
        @Qualifier("applicationIODispatcher") dispatcher: CoroutineDispatcher,
    ): CoroutineScope {
        return CoroutineScope(dispatcher)
    }
}
