package personal.brian.payment.externals.rdb.transaction.executor

import org.springframework.stereotype.Component

/**
 * @author Brian
 * @since 2025. 8. 4.
 */
@Component
class Tx(
    private val _txAdvice: TxAdvice,
) {
    init {
        Tx.txAdvice = _txAdvice
    }

    companion object {
        private lateinit var txAdvice: TxAdvice

        fun <T> writable(function: () -> T): T {
            return txAdvice.writable(function)
        }

        fun <T> readable(function: () -> T): T {
            return txAdvice.readable(function)
        }
    }
}
