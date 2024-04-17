package dev.kevinsalazar.exchange.infraestructure.events.internal

import dev.kevinsalazar.exchange.domain.events.Event
import dev.kevinsalazar.exchange.domain.events.InternalEvent
import dev.kevinsalazar.exchange.domain.ports.driven.events.InternalEventBus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DefaultInternalEventBus : InternalEventBus {

    private val events = MutableSharedFlow<Event>()

    override suspend fun publish(event: InternalEvent) {
        events.emit(event)
    }

    override fun subscribe(): Flow<Event> = events
}