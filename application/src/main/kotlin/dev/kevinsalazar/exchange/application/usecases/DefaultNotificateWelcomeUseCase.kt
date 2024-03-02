package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.events.UserRegisteredEvent
import dev.kevinsalazar.exchange.domain.ports.driving.NotificateWelcomeUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.events.ExternalEventBus

class DefaultNotificateWelcomeUseCase(
    private val externalEventBus: ExternalEventBus
) : NotificateWelcomeUseCase {

    override suspend fun execute(event: UserRegisteredEvent) {
        println("NotificateWelcomeUseCase: OK")
    }
}