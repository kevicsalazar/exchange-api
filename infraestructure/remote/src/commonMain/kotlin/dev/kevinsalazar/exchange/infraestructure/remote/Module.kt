package dev.kevinsalazar.exchange.infraestructure.remote

import dev.kevinsalazar.exchange.infraestructure.remote.api.CurrencyApi
import dev.kevinsalazar.exchange.infraestructure.remote.config.RemoteConfig
import dev.kevinsalazar.exchange.infraestructure.remote.repository.DefaultCurrenciesRepository
import dev.kevinsalazar.exchange.domain.ports.driven.CurrenciesRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val remoteModule = module {

    single { RemoteConfig(get()).get() }

    factoryOf(::CurrencyApi)
    factoryOf(::DefaultCurrenciesRepository) withOptions { bind<CurrenciesRepository>() }
}

