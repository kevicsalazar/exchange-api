package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.events.UserRegisteredEvent
import dev.kevinsalazar.exchange.domain.ports.driving.NotificateWelcomeUseCase

class DefaultNotificateWelcomeUseCase : NotificateWelcomeUseCase {

    override suspend fun execute(event: UserRegisteredEvent) {
        println("NotificateWelcomeUseCase: OK")
    }
}