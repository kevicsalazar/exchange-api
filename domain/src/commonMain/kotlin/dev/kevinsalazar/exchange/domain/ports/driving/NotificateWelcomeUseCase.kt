package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.events.UserRegisteredEvent
import dev.kevinsalazar.exchange.domain.ports.driving.events.EventHandler

interface NotificateWelcomeUseCase : EventHandler<UserRegisteredEvent> {
    override suspend fun getEventType() = UserRegisteredEvent::class
}
