package dev.kevinsalazar.exchange.domain.ports.driving.events

import dev.kevinsalazar.exchange.domain.events.Event
import kotlinx.coroutines.flow.Flow

interface EventBus {
    suspend fun publish(event: Event)
    fun subscribe(): Flow<Event>
}