package dev.kevinsalazar.exchange.domain.ports.driven.events

import dev.kevinsalazar.exchange.domain.enums.Queue
import dev.kevinsalazar.exchange.domain.events.Event
import kotlinx.coroutines.flow.Flow

interface EventBus : InternalEventBus, ExternalEventBus

interface InternalEventBus {
    suspend fun publish(event: Event)
    fun subscribe(): Flow<Event>
}

interface ExternalEventBus {
    suspend fun publish(event: Event, queue: Queue)
}