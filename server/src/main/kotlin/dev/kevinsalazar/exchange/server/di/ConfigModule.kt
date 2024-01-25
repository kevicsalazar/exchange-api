package dev.kevinsalazar.exchange.server.di

import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.server.utils.AppConfigProperties
import dev.kevinsalazar.exchange.server.utils.JwtSecurityConfig
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val configModule = module {
    single { AppConfigProperties.getInstance().jwt } withOptions { bind<ConfigProperties.Jwt>() }
    single { AppConfigProperties.getInstance().database } withOptions { bind<ConfigProperties.Database>() }
    single { JwtSecurityConfig(get()) } withOptions { bind<SecurityConfig>() }
}