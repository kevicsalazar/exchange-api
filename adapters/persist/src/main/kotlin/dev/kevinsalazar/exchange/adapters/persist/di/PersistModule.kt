package dev.kevinsalazar.exchange.adapters.persist.di

import dev.kevinsalazar.exchange.adapters.persist.repository.DefaultTransactionRepository
import dev.kevinsalazar.exchange.adapters.persist.repository.DefaultFundsRepository
import dev.kevinsalazar.exchange.adapters.persist.repository.DefaultUserRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driven.FundsRepository
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val persistModule = module {
    factoryOf(::DefaultFundsRepository) withOptions { bind<FundsRepository>() }
    factoryOf(::DefaultTransactionRepository) withOptions { bind<TransactionRepository>() }
    factoryOf(::DefaultUserRepository) withOptions { bind<UserRepository>() }
}