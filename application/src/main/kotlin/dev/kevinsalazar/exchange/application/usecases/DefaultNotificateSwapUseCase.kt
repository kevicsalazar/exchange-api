package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Transaction
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

class DefaultNotificateSwapUseCase(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository,
    private val eventBus: EventBus
) : NotificateSwapUseCase {

    override suspend fun execute(event: SuccessfulSwapEvent) {

        val transaction = transactionRepository.findById(event.transactionId) ?: return
        val user = userRepository.findById(transaction.userId) ?: return

        publishEmail(user, transaction)
    }

    private suspend fun publishEmail(user: User, transaction: Transaction) {
        val data = SwapEmail(
            name = user.name,
            sentAmount = "",
            receivedAmount = ""
        )
        val event = EmailEvent(
            template = EmailTemplate.Swap.name,
            destination = user.email,
            data = Json.encodeToString(data)
        )
        eventBus.publish(event, Queue.Email)
    }

}