package dev.kevinsalazar.exchange.application.events

import dev.kevinsalazar.exchange.domain.events.Event
import dev.kevinsalazar.exchange.domain.ports.driving.events.EventBus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DefaultEventBus : EventBus {
    private val eventFlow = MutableSharedFlow<Event>()

    override suspend fun publish(event: Event) {
        eventFlow.emit(event)
    }

    override fun subscribe(): Flow<Event> = eventFlow
}