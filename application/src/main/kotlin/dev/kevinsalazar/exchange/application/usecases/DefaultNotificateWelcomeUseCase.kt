package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.enums.Queue
import dev.kevinsalazar.exchange.domain.events.NotificationEvent
import dev.kevinsalazar.exchange.domain.events.UserRegisteredEvent
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driving.NotificateWelcomeUseCase

class DefaultNotificateWelcomeUseCase(
    private val eventBus: EventBus
) : NotificateWelcomeUseCase {

    override suspend fun execute(event: UserRegisteredEvent) {

        val email = NotificationEvent.Email(
            email = "",
            message = ""
        )

        val e = NotificationEvent(
            email = email
        )

        eventBus.publish(e, Queue.NOTIFICATION)
    }
}