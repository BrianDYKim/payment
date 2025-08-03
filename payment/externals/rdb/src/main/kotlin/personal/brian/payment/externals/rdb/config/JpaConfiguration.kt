package personal.brian.payment.externals.rdb.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * @author Brian
 * @since 2025. 8. 4.
 */
@Configuration
@EnableJpaAuditing
class JpaConfiguration(
    @PersistenceContext(unitName = "main")
    @Qualifier("entityManagerFactory")
    private val entityManager: EntityManager,
) {
    @Primary
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}
