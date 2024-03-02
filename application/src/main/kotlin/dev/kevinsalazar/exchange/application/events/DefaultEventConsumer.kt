package dev.kevinsalazar.exchange.application.events

import dev.kevinsalazar.exchange.domain.events.Event
import dev.kevinsalazar.exchange.domain.ports.driving.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driving.events.EventConsumer
import dev.kevinsalazar.exchange.domain.ports.driving.events.EventHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

class DefaultEventConsumer(
    private val eventBus: EventBus
) : CoroutineScope, KoinComponent, EventConsumer {

    private val handlers by lazy { getKoin().getAll<EventHandler<out Event>>() }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    override fun start() {
        launch {
            eventBus.subscribe()
                .collect { event ->
                    handlers.onEach { handler ->
                        handler.safeExecute(event)
                    }
                }
        }
    }

    override fun stop() {
        job.cancel()
    }
}