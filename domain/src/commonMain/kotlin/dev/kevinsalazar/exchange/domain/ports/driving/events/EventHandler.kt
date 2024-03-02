package dev.kevinsalazar.exchange.domain.ports.driving.events

import dev.kevinsalazar.exchange.domain.events.Event
import kotlin.reflect.KClass
import kotlin.reflect.safeCast

interface EventHandler<T : Event> {

    suspend fun getEventType(): KClass<T>
    suspend fun safeExecute(event: Event) {
        getEventType().safeCast(event)
            ?.also { execute(it) }
    }

    suspend fun execute(event: T)
}
