package personal.brian.jpaExtension.hibernate

/**
 * @author Brian
 * @since 2025. 8. 3.
 */
object HibernateProperties {
    fun getPropertiesMap(dialect: String): Map<String, String> {
        val properties = mutableMapOf<String, String>()

        properties["hibernate.format_sql"] = "true"
        properties["hibernate.highlight_sql"] = "true"
        properties["hibernate.hbm2ddl.auto"] = "none"
        properties["hibernate.ddl-auto"] = "none"
        properties["hibernate.dialect"] = dialect

        return properties
    }
}
