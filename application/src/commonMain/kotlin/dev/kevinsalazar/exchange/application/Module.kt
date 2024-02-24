package dev.kevinsalazar.exchange.application

import dev.kevinsalazar.exchange.application.usecases.*
import dev.kevinsalazar.exchange.application.usecases.DefaultListCurrenciesUseCase
import dev.kevinsalazar.exchange.application.usecases.DefaultRegisterUseCase
import dev.kevinsalazar.exchange.application.usecases.DefaultSwapUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val applicationModule = module {
    factoryOf(::DefaultConvertCurrencyUseCase) withOptions { bind<ConvertCurrencyUseCase>() }
    factoryOf(::DefaultSwapUseCase) withOptions { bind<SwapUseCase>() }
    factoryOf(::DefaultListCurrenciesUseCase) withOptions { bind<ListCurrenciesUseCase>() }
    factoryOf(::DefaultLoginUseCase) withOptions { bind<LoginUseCase>() }
    factoryOf(::DefaultRegisterUseCase) withOptions { bind<RegisterUseCase>() }
}