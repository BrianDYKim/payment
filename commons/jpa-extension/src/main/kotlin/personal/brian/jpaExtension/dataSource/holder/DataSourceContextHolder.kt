package personal.brian.jpaExtension.dataSource.holder

import personal.brian.jpaExtension.dataSource.type.DataSourceType

/**
 * @author Brian
 * @since 2025. 8. 3.
 */
object DataSourceContextHolder {
    private val contextHolder = ThreadLocal<DataSourceType>()
    var dataSourceType: DataSourceType
        get() = contextHolder.get()
        set(value) {
            contextHolder.set(value)
        }

    init {
        dataSourceType = DataSourceType.WRITE
    }

    fun cleanDataSourceType() {
        contextHolder.remove()
    }
}
