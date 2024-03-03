package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.enums.Queue
import dev.kevinsalazar.exchange.domain.events.NotificationEvent
import dev.kevinsalazar.exchange.domain.events.SuccessfulSwapEvent
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driving.NotificateSwapUseCase

class DefaultNotificateSwapUseCase(
    private val eventBus: EventBus
) : NotificateSwapUseCase {

    override suspend fun execute(event: SuccessfulSwapEvent) {

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