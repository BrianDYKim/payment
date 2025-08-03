package personal.brian.jpaExtension.dataSource.ifs

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
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
    ): DataSource

    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        routingDataSource: BaseRoutingDataSource,
    )
}
