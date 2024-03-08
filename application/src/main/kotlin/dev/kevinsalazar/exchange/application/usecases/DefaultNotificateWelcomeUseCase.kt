package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.enums.Queue
import dev.kevinsalazar.exchange.domain.events.EmailEvent
import dev.kevinsalazar.exchange.domain.events.UserRegisteredEvent
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driving.NotificateWelcomeUseCase
import dev.kevinsalazar.exchange.domain.values.WelcomeEmail
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DefaultNotificateWelcomeUseCase(
    private val userRepository: UserRepository,
    private val eventBus: EventBus
) : NotificateWelcomeUseCase {

    override suspend fun execute(event: UserRegisteredEvent) {

        val user = userRepository.findById(event.userId) ?: return

        publishEmail(user)
    }

    private suspend fun publishEmail(user: User) {
        val data = WelcomeEmail(
            name = user.name
        )
        val event = EmailEvent(
            template = WelcomeEmail.TEMPLATE,
            destination = user.email,
            data = Json.encodeToString(data)
        )
        eventBus.publish(event, Queue.NotificationEmail)
    }
}