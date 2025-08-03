package personal.brian.payment.externals.rdb.dataSource.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import personal.brian.jpaExtension.dataSource.ifs.DataSourceConfigurationInterface
import personal.brian.jpaExtension.dataSource.type.BaseRoutingDataSource
import personal.brian.jpaExtension.dataSource.type.DataSourceType
import personal.brian.jpaExtension.hibernate.HibernateProperties
import personal.brian.payment.externals.rdb.dataSource.routingDataSource.CustomRoutingDataSource
import javax.sql.DataSource
import kotlin.math.min

/**
 * @author Brian
 * @since 2025. 8. 4.
 */
@Configuration
@EnableJpaRepositories(
    basePackages = ["personal.brian.payment.externals.rdb.table"],
    entityManagerFactoryRef = "customEntityManagerFactory",
    transactionManagerRef = "transactionManager",
)
class CustomDataSourceConfiguration(
    @Value("\${spring.datasource.main.write.url}") private val writeUrl: String,
    @Value("\${spring.datasource.main.write.username}") private val writeUsername: String,
    @Value("\${spring.datasource.main.write.password}") private val writePassword: String,
    @Value("\${spring.datasource.main.read.url}") private val readUrl: String,
    @Value("\${spring.datasource.main.read.username}") private val readUsername: String,
    @Value("\${spring.datasource.main.read.password}") private val readPassword: String,
    @Value("\${spring.datasource.main.driver-class-name}") private val driverClassName: String,
) : DataSourceConfigurationInterface {
    private val availableCpuCoreNumber by lazy { Runtime.getRuntime().availableProcessors() }

    @Bean(name = ["readDataSource"])
    override fun readDataSource(): DataSource {
        return generateDataSource(readUrl, readUsername, readPassword)
    }

    @Bean(name = ["writeDataSource"])
    override fun writeDataSource(): DataSource {
        return generateDataSource(writeUrl, writeUsername, writePassword)
    }

    @Primary
    @Bean(name = ["customRoutingDataSource"])
    override fun routingDataSource(
        @Qualifier("readDataSource") readDataSource: DataSource,
        @Qualifier("writeDataSource") writeDataSource: DataSource,
    ): BaseRoutingDataSource {
        val dataSourceMap = HashMap<Any, Any>()

        val writeDataSourceKey = DataSourceType.WRITE.key
        dataSourceMap[writeDataSourceKey] = writeDataSource

        val readDataSourceKey = DataSourceType.READ.key
        dataSourceMap[readDataSourceKey] = readDataSource

        return CustomRoutingDataSource().apply {
            this.setDefaultTargetDataSource(writeDataSource)
            this.setTargetDataSources(dataSourceMap)
        }
    }

    @Primary
    @Bean(name = ["entityManagerFactory"])
    override fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("customRoutingDataSource") routingDataSource: BaseRoutingDataSource,
    ): LocalContainerEntityManagerFactoryBean {
        val dialect = "org.hibernate.dialect.MySQL8Dialect"
        val properties = HibernateProperties.getPropertiesMap(dialect = dialect)

        return builder
            .dataSource(routingDataSource)
            .packages("personal.brian.payment.externals.rdb.table")
            .persistenceUnit("main")
            .properties(properties)
            .build()
    }

    @Primary
    @Bean(name = ["transactionManager"])
    override fun transactionManager(
        @Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory,
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    fun generateDataSource(
        url: String,
        username: String,
        password: String,
    ): DataSource {
        val dataSource =
            DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build()

        val hikariConfig =
            HikariConfig().apply {
                this.dataSource = dataSource
                this.maximumPoolSize = availableCpuCoreNumber * 2 + 1
                this.minimumIdle = min(2, availableCpuCoreNumber * 2 + 1)
            }

        return HikariDataSource(hikariConfig)
    }
}
