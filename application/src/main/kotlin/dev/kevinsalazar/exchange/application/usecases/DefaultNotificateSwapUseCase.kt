package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.enums.EmailTemplate
import dev.kevinsalazar.exchange.domain.enums.Queue
import dev.kevinsalazar.exchange.domain.events.EmailEvent
import dev.kevinsalazar.exchange.domain.events.SuccessfulSwapEvent
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driving.NotificateSwapUseCase
import dev.kevinsalazar.exchange.domain.values.SwapEmail
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.NumberFormat
import java.util.*

class DefaultNotificateSwapUseCase(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository,
    private val eventBus: EventBus
) : NotificateSwapUseCase {

    override suspend fun execute(event: SuccessfulSwapEvent) {

        val transaction = transactionRepository.findById(event.transactionId) ?: return
        val user = userRepository.findById(transaction.userId) ?: return

        val sentCurrencyCode = transaction.sentCurrencyCode ?: return
        val sentAmount = transaction.sentAmount ?: return
        val receivedCurrencyCode = transaction.receivedCurrencyCode ?: return
        val receivedAmount = transaction.receivedAmount ?: return

        val sentAmountFormatted = formatAmount(sentCurrencyCode, sentAmount)
        val receivedAmountFormatted = formatAmount(receivedCurrencyCode, receivedAmount)

        publishEmail(user, sentAmountFormatted, receivedAmountFormatted)
    }

    private suspend fun publishEmail(
        user: User,
        sentAmount: String,
        receivedAmount: String
    ) {
        val data = SwapEmail(
            name = user.name,
            sentAmount = sentAmount,
            receivedAmount = receivedAmount
        )
        val event = EmailEvent(
            template = EmailTemplate.Swap.name,
            destination = user.email,
            data = Json.encodeToString(data)
        )
        eventBus.publish(event, Queue.Email)
    }

    private fun formatAmount(code: String, amount: Float): String {

        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        val formattedAmount = numberFormat.format(amount)

        return "$formattedAmount $code"
    }

}