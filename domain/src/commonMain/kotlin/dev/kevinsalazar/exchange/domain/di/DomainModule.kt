package dev.kevinsalazar.exchange.domain.di

import dev.kevinsalazar.exchange.domain.ports.driving.*
import dev.kevinsalazar.exchange.domain.usecases.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::DefaultConvertCurrencyUseCase) withOptions { bind<ConvertCurrencyUseCase>() }
    factoryOf(::DefaultExchangeUseCase) withOptions { bind<ExchangeUseCase>() }
    factoryOf(::DefaultListCurrenciesUseCase) withOptions { bind<ListCurrenciesUseCase>() }
    factoryOf(::DefaultLoginUseCase) withOptions { bind<LoginUseCase>() }
    factoryOf(::DefaultRegisterUseCase) withOptions { bind<RegisterUseCase>() }
}