package personal.brian.jpaExtension.dataSource.ifs

import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import personal.brian.jpaExtension.dataSource.type.BaseRoutingDataSource
import javax.sql.DataSource

/**
 * @author Brian
 * @since 2025. 8. 3.
 */
interface DataSourceConfigurationInterface {
    fun readDataSource(): DataSource

    fun writeDataSource(): DataSource

    fun routingDataSource(
        readDataSource: DataSource,
        writeDataSource: DataSource,
    ): BaseRoutingDataSource

    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        routingDataSource: BaseRoutingDataSource,
    ): LocalContainerEntityManagerFactoryBean

    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager
}
