package dev.kevinsalazar.exchange.infraestructure.events

import dev.kevinsalazar.exchange.domain.ports.driving.events.ExternalEventBus
import dev.kevinsalazar.exchange.infraestructure.events.external.DefaultExternalEventBus
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val eventsModule = module {
    singleOf(::DefaultExternalEventBus) withOptions { bind<ExternalEventBus>() }
}