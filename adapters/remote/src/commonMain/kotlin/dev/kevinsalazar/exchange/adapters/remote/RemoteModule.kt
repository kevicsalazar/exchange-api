package dev.kevinsalazar.exchange.adapters.remote

import dev.kevinsalazar.exchange.adapters.persist.repository.DefaultExchangeRateRepository
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRateRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val remoteModule = module {
    factoryOf(::DefaultExchangeRateRepository) withOptions { bind<ExchangeRateRepository>() }
}

