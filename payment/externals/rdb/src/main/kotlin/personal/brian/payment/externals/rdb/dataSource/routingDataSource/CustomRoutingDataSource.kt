package personal.brian.payment.externals.rdb.dataSource.routingDataSource

import personal.brian.jpaExtension.dataSource.type.BaseRoutingDataSource
import personal.brian.payment.externals.rdb.dataSource.holder.DataSourceContextHolder

/**
 * @author Brian
 * @since 2025. 8. 4.
 */
class CustomRoutingDataSource() : BaseRoutingDataSource() {
    override fun determineCurrentLookupKey(): String {
        return DataSourceContextHolder.dataSourceType.key
    }
}
