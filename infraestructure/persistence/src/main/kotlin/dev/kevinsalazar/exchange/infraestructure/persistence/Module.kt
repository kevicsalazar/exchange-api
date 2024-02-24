package dev.kevinsalazar.exchange.infraestructure.persistence

import dev.kevinsalazar.exchange.infraestructure.persistence.config.DatabaseConfig
import dev.kevinsalazar.exchange.infraestructure.persistence.repository.DefaultFundsRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.repository.DefaultTransactionRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.repository.DefaultUserRepository
import dev.kevinsalazar.exchange.domain.ports.driven.FundsRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import org.koin.core.module.dsl.*
import org.koin.dsl.module

val persistModule = module {
    singleOf(::DatabaseConfig) withOptions { createdAtStart() }
    factoryOf(::DefaultFundsRepository) withOptions { bind<FundsRepository>() }
    factoryOf(::DefaultTransactionRepository) withOptions { bind<TransactionRepository>() }
    factoryOf(::DefaultUserRepository) withOptions { bind<UserRepository>() }
}