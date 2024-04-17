package dev.kevinsalazar.exchange.infraestructure.events

import dev.kevinsalazar.exchange.domain.events.Event
import dev.kevinsalazar.exchange.domain.events.ExternalEvent
import dev.kevinsalazar.exchange.domain.events.InternalEvent
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driven.events.ExternalEventBus
import dev.kevinsalazar.exchange.domain.ports.driven.events.InternalEventBus
import kotlinx.coroutines.flow.Flow

class DefaultEventBus(
    private val internalEventBus: InternalEventBus,
    private val externalEventBus: ExternalEventBus
) : EventBus {

    override fun subscribe(): Flow<Event> {
        return internalEventBus.subscribe()
    }

    override suspend fun publish(event: Event) {
        when (event) {
            is InternalEvent -> internalEventBus.publish(event)
            is ExternalEvent -> externalEventBus.publish(event)
        }
    }
}