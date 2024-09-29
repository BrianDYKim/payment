package team.brian.payment.payment.application.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.dao.DataIntegrityViolationException
import reactor.test.StepVerifier
import team.brian.payment.payment.application.port.inbound.CheckoutCommand
import team.brian.payment.payment.application.port.inbound.CheckoutUseCase
import team.brian.payment.payment.test.PaymentDatabaseHelper
import team.brian.payment.payment.test.PaymentTestConfiguration
import java.util.UUID

@SpringBootTest
@Import(PaymentTestConfiguration::class)
class CheckoutServiceTest(
    @Autowired private val checkoutUseCase: CheckoutUseCase,
    @Autowired private val paymentDatabaseHelper: PaymentDatabaseHelper,
) {
    @BeforeEach
    fun setup() {
        paymentDatabaseHelper.clean().block()
    }

    @Test
    fun `should save PaymentEvent and PaymentOrder successfully`() {
        val orderId: String = UUID.randomUUID().toString()
        val checkoutCommand =
            CheckoutCommand(
                cartId = 1,
                buyerId = 1,
                productIdList = listOf(1, 2, 3),
                idempotencyKey = orderId,
            )

        StepVerifier.create(checkoutUseCase.checkout(checkoutCommand))
            .expectNextMatches {
                it.amount.toInt() == 60000 && it.orderId == orderId
            }
            .verifyComplete()

        val paymentEvent = paymentDatabaseHelper.getPayments(orderId)!!

        assertEquals(paymentEvent.orderId, orderId)
        assertEquals(paymentEvent.totalAmount(), 60000)
        assertEquals(paymentEvent.paymentOrders.size, checkoutCommand.productIdList.size)
        assertFalse(paymentEvent.isPaymentDone())
        assertTrue(paymentEvent.paymentOrders.all { !it.isLedgerUpdated() })
        assertTrue(paymentEvent.paymentOrders.all { !it.isWalletUpdated() })
    }

    @Test
    fun `should fail to save Payment and PaymentOrder when trying to save for the second time`() {
        val orderId: String = UUID.randomUUID().toString()
        val checkoutCommand =
            CheckoutCommand(
                cartId = 1,
                buyerId = 1,
                productIdList = listOf(1, 2, 3),
                idempotencyKey = orderId,
            )

        checkoutUseCase.checkout(checkoutCommand).block()

        assertThrows<DataIntegrityViolationException> {
            checkoutUseCase.checkout(checkoutCommand).block()
        }
    }
}
