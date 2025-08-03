package personal.brian.payment.externals.rdb.dataSource.holder

import personal.brian.jpaExtension.dataSource.holder.AbstractDataSourceContextHolder
import personal.brian.jpaExtension.dataSource.type.DataSourceType

/**
 * @author Brian
 * @since 2025. 8. 4.
 */
object DataSourceContextHolder : AbstractDataSourceContextHolder() {
    private val contextHolder = ThreadLocal<DataSourceType>()

    override var dataSourceType: DataSourceType
        get() = contextHolder.get()
        set(value) {
            contextHolder.set(value)
        }

    init {
        dataSourceType = DataSourceType.WRITE
    }

    override fun cleanDataSourceType() {
        contextHolder.remove()
    }
}
