package dev.kevinsalazar.exchange.infraestructure.events

import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventConsumer
import dev.kevinsalazar.exchange.domain.ports.driven.events.ExternalEventBus
import dev.kevinsalazar.exchange.domain.ports.driven.events.InternalEventBus
import dev.kevinsalazar.exchange.infraestructure.events.external.DefaultExternalEventBus
import dev.kevinsalazar.exchange.infraestructure.events.internal.DefaultInternalEventBus
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val eventsModule = module {

    singleOf(::DefaultEventBus) withOptions { bind<EventBus>() }
    singleOf(::DefaultEventConsumer) withOptions { bind<EventConsumer>() }

    singleOf(::DefaultExternalEventBus) withOptions { bind<ExternalEventBus>() }
    singleOf(::DefaultInternalEventBus) withOptions { bind<InternalEventBus>() }

}