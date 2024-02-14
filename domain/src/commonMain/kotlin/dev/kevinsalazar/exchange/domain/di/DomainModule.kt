package dev.kevinsalazar.exchange.domain.di

import dev.kevinsalazar.exchange.domain.ports.driving.ExchangeRateUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.ExchangeUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.LoginUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultExchangeRateUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultExchangeUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultLoginUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultRegisterUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::DefaultExchangeRateUseCase) withOptions { bind<ExchangeRateUseCase>() }
    factoryOf(::DefaultExchangeUseCase) withOptions { bind<ExchangeUseCase>() }
    factoryOf(::DefaultLoginUseCase) withOptions { bind<LoginUseCase>() }
    factoryOf(::DefaultRegisterUseCase) withOptions { bind<RegisterUseCase>() }
}