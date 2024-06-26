package dev.kevinsalazar.exchange.application

import dev.kevinsalazar.exchange.application.usecases.*
import dev.kevinsalazar.exchange.domain.ports.driving.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val applicationModule = module {

    factoryOf(::DefaultConvertUseCase) withOptions { bind<ConvertUseCase>() }
    factoryOf(::DefaultDepositUseCase) withOptions { bind<DepositUseCase>() }
    factoryOf(::DefaultListBalancesUseCase) withOptions { bind<ListBalancesUseCase>() }
    factoryOf(::DefaultSwapUseCase) withOptions { bind<SwapUseCase>() }
    factoryOf(::DefaultListCurrenciesUseCase) withOptions { bind<ListCurrenciesUseCase>() }
    factoryOf(::DefaultListTransactionsUseCase) withOptions { bind<ListTransactionsUseCase>() }
    factoryOf(::DefaultLoginUseCase) withOptions { bind<LoginUseCase>() }
    factoryOf(::DefaultRegisterUseCase) withOptions { bind<RegisterUseCase>() }
    factoryOf(::DefaultWithdrawalUseCase) withOptions { bind<WithdrawalUseCase>() }

}