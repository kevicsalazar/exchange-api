package dev.kevinsalazar.exchange.adapters.api.di

import dev.kevinsalazar.exchange.adapters.api.utils.JwtSecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val apiModule = module {
    single { JwtSecurityConfig(get()) } withOptions { bind<SecurityConfig>() }
}