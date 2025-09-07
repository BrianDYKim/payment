package personal.brian.payment.domain.price

import java.math.BigDecimal

/**
 * @author Doyeop Kim
 * @since 2025. 9. 7.
 */
data class Price(
    val amount: BigDecimal,
    val currency: Currency,
) : Comparable<Price> {
    val isDollar: Boolean get() = currency == Currency.USD

    override fun compareTo(other: Price): Int {
        require(currency == other.currency) { "Price comparison must be in the same currency" }
        return amount.compareTo(other.amount)
    }

    operator fun plus(price: Price): Price {
        require(currency == price.currency) { "Cannot add prices in different currencies" }
        return Price(amount + price.amount, currency)
    }

    operator fun minus(price: Price): Price {
        require(currency == price.currency) { "Cannot subtract prices in different currencies" }
        return Price(amount - price.amount, currency)
    }

    operator fun times(price: Price): Price {
        require(currency == price.currency) { "Cannot multiply prices in different currencies" }
        return Price(amount * price.amount, currency)
    }

    fun different(price: Price): Boolean = this != price

    fun isSameCurrency(price: Price): Boolean = currency == price.currency

    fun changeCurrency(
        to: Currency,
        exchangeRate: BigDecimal,
    ): Price {
        return Price.of(amount * exchangeRate, to)
    }

    fun isZeroPrice(): Boolean {
        return compareTo(Price.of(BigDecimal.ZERO, currency)) == 0
    }

    companion object {
        val ZERO_KRW = Price(BigDecimal.ZERO, Currency.KRW)
        val ZERO_USD = Price(BigDecimal.ZERO, Currency.USD)

        fun of(
            amount: BigDecimal,
            currency: Currency,
        ): Price {
            return Price(amount, currency)
        }

        fun zero(currency: Currency) = of(BigDecimal.ZERO, currency)
    }
}
