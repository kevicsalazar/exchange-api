package dev.kevinsalazar.exchange.infraestructure.api

import dev.kevinsalazar.exchange.infraestructure.api.config.DatabaseConfigProperties
import dev.kevinsalazar.exchange.infraestructure.api.config.JwtConfigProperties
import dev.kevinsalazar.exchange.infraestructure.api.config.RemoteConfigProperties
import dev.kevinsalazar.exchange.infraestructure.api.utils.JwtSecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val apiModule = module {
    singleOf(::JwtConfigProperties) withOptions { bind<ConfigProperties.Jwt>() }
    singleOf(::DatabaseConfigProperties) withOptions { bind<ConfigProperties.Database>() }
    singleOf(::RemoteConfigProperties) withOptions { bind<ConfigProperties.Remote>() }
    singleOf(::JwtSecurityConfig) withOptions { bind<SecurityConfig>() }
}