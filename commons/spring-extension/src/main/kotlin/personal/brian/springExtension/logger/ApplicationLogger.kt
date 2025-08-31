package personal.brian.springExtension.logger

import org.slf4j.LoggerFactory

class ApplicationLogger(private val clazz: Class<out Any>) {
    private val logger by lazy { LoggerFactory.getLogger(clazz) }

    fun info(message: String) {
        val locationTag = getLocationTag()
        logger.info("[$locationTag] $message")
    }

    fun warn(message: String) {
        val locationTag = getLocationTag()
        logger.warn("[$locationTag] $message")
    }

    fun warn(
        message: String,
        throwable: Throwable?,
    ) {
        val locationTag = getLocationTag()
        logger.warn("[$locationTag] $message", throwable)
    }

    fun error(
        message: String?,
        throwable: Throwable? = null,
    ) {
        val locationTag = getLocationTag()
        logger.error("[$locationTag] $message", throwable)
    }

    private fun getLocationTag(): String {
        val stackTrace = Thread.currentThread().stackTrace
        val stackElement = stackTrace[3] // 호출한 메소드의 스택 프레임
        val className = stackElement.className.substringAfterLast('.')
        val methodName = stackElement.methodName
        return "$className.$methodName"
    }
}
