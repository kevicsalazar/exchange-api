package dev.kevinsalazar.exchange.infraestructure.persistence

import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.domain.ports.driven.CurrencyRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.config.DatabaseConfig
import dev.kevinsalazar.exchange.infraestructure.persistence.config.InitialDataLoader
import dev.kevinsalazar.exchange.infraestructure.persistence.repository.DefaultBalanceRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.repository.DefaultCurrencyRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.repository.DefaultTransactionRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.repository.DefaultUserRepository
import org.koin.core.module.dsl.*
import org.koin.dsl.module

val persistModule = module {

    singleOf(::InitialDataLoader)
    singleOf(::DatabaseConfig) withOptions { createdAtStart() }

    factoryOf(::DefaultBalanceRepository) withOptions { bind<BalanceRepository>() }
    factoryOf(::DefaultCurrencyRepository) withOptions { bind<CurrencyRepository>() }
    factoryOf(::DefaultTransactionRepository) withOptions { bind<TransactionRepository>() }
    factoryOf(::DefaultUserRepository) withOptions { bind<UserRepository>() }
}