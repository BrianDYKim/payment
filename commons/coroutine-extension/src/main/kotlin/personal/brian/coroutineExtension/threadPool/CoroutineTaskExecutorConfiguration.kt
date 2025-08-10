package personal.brian.coroutineExtension.threadPool

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

/**
 * @author Doyeop Kim
 * @since 2025. 8. 10.
 */
@Configuration
class CoroutineTaskExecutorConfiguration {
    companion object {
        const val CORE_POOL_SIZE_MULTIPLIER = 10 // 현재 가상환경의 cpu 코어 10배만큼 pool size 할당
        const val MAX_POOL_SIZE_MULTIPLIER = 20 // 현재 가상환경의 cpu 코어 20배만큼 pool size 할당
        const val QUEUE_CAPACITY_MULTIPLIER = 100 // queue capacity는 cpu 코어의 100배만큼 size 할당
    }

    @Bean(name = ["coroutineExecutor"])
    fun coroutineExecutor(): Executor =
        ThreadPoolTaskExecutor().apply {
            corePoolSize = Runtime.getRuntime().availableProcessors() * CORE_POOL_SIZE_MULTIPLIER
            maxPoolSize = Runtime.getRuntime().availableProcessors() * MAX_POOL_SIZE_MULTIPLIER
            queueCapacity = Runtime.getRuntime().availableProcessors() * QUEUE_CAPACITY_MULTIPLIER
            setThreadNamePrefix("coroutineExecutor-") // Async 스레드 이름은 coroutineExecutor- 로 시작
            initialize()
        }
}
