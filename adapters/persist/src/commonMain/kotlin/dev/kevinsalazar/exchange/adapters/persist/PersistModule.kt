package dev.kevinsalazar.exchange.adapters.persist

import dev.kevinsalazar.exchange.adapters.persist.repository.DefaultExchangeRepository
import dev.kevinsalazar.exchange.adapters.persist.repository.DefaultUserRepository
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val persistModule = module {
    factoryOf(::DefaultExchangeRepository) withOptions { bind<ExchangeRepository>() }
    factoryOf(::DefaultUserRepository) withOptions { bind<UserRepository>() }
}