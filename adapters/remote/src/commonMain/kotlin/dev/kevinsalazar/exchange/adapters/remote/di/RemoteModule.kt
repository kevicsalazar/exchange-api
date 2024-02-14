package dev.kevinsalazar.exchange.adapters.remote.di

import dev.kevinsalazar.exchange.adapters.remote.api.CoinMarketCapApi
import dev.kevinsalazar.exchange.adapters.remote.config.RemoteConfig
import dev.kevinsalazar.exchange.adapters.remote.repository.DefaultExchangeRateRepository
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRateRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val remoteModule = module {

    single { RemoteConfig(get()).get() }

    factoryOf(::CoinMarketCapApi)
    factoryOf(::DefaultExchangeRateRepository) withOptions { bind<ExchangeRateRepository>() }
}

