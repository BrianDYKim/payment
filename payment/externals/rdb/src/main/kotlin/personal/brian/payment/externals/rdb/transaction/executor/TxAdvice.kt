package personal.brian.payment.externals.rdb.transaction.executor

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import personal.brian.payment.externals.rdb.transaction.annotation.CustomTransactional

/**
 * @author Brian
 * @since 2025. 8. 4.
 */
@Component
class TxAdvice {
    @CustomTransactional(readOnly = false)
    fun <T> writable(function: () -> T): T {
        return execute(function)
    }

    @CustomTransactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    fun <T> readable(function: () -> T): T {
        return execute(function)
    }

    private fun <T> execute(function: () -> T): T {
        return function.invoke()
    }
}
