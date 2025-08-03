package personal.brian.jpaExtension.dataSource.holder

import personal.brian.jpaExtension.dataSource.type.DataSourceType

/**
 * @author Brian
 * @since 2025. 8. 4.
 */
abstract class AbstractDataSourceContextHolder {
    abstract var dataSourceType: DataSourceType

    abstract fun cleanDataSourceType()
}
