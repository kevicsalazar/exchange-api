package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.events.SuccessfulSwapEvent
import dev.kevinsalazar.exchange.domain.ports.driving.events.EventHandler

interface NotificateSwapUseCase : EventHandler<SuccessfulSwapEvent> {
    override suspend fun getEventType() = SuccessfulSwapEvent::class
}
