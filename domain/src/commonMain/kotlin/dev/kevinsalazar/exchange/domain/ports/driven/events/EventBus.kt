package dev.kevinsalazar.exchange.domain.ports.driven.events

import dev.kevinsalazar.exchange.domain.events.Event
import dev.kevinsalazar.exchange.domain.events.ExternalEvent
import dev.kevinsalazar.exchange.domain.events.InternalEvent
import kotlinx.coroutines.flow.Flow

interface EventBus {
    suspend fun publish(event: Event)
    fun subscribe(): Flow<Event>
}

interface InternalEventBus {
    suspend fun publish(event: InternalEvent)
    fun subscribe(): Flow<Event>
}

interface ExternalEventBus {
    suspend fun publish(event: ExternalEvent)
}