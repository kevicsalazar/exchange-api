package dev.kevinsalazar.exchange.domain

import dev.kevinsalazar.exchange.domain.ports.driving.ExchangeRateUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.ExchangeUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.SignInUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.SignUpUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultExchangeRateUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultExchangeUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultSignInUseCase
import dev.kevinsalazar.exchange.domain.usecases.DefaultSignUpUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::DefaultExchangeRateUseCase) withOptions { bind<ExchangeRateUseCase>() }
    factoryOf(::DefaultExchangeUseCase) withOptions { bind<ExchangeUseCase>() }
    factoryOf(::DefaultSignInUseCase) withOptions { bind<SignInUseCase>() }
    factoryOf(::DefaultSignUpUseCase) withOptions { bind<SignUpUseCase>() }
}