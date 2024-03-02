package dev.kevinsalazar.exchange.domain.ports.driving.events

import dev.kevinsalazar.exchange.domain.events.Event

interface ExternalEventBus {
    suspend fun publish(event: Event)
}