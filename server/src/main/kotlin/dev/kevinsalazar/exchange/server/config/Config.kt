package dev.kevinsalazar.exchange.server.config

import dev.kevinsalazar.exchange.adapters.persist.config.DatabaseConfig
import dev.kevinsalazar.exchange.adapters.persist.persistModule
import dev.kevinsalazar.exchange.adapters.remote.remoteModule
import dev.kevinsalazar.exchange.domain.domainModule
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.server.di.configModule
import dev.kevinsalazar.exchange.server.utils.AppConfigProperties
import dev.kevinsalazar.exchange.server.utils.JwtSecurityConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.core.logger.Level
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger(level = Level.INFO)
        modules(
            configModule,
            persistModule,
            remoteModule,
            domainModule,
        )
    }
}

fun Application.configureProperties() {
    AppConfigProperties.initialize(this)
}

fun Application.configureAuthentication() {

    val jwtSecurityConfig by inject<JwtSecurityConfig>()

    install(Authentication) {
        jwt {
            verifier(jwtSecurityConfig.verifier)
            validate {
                val claim = it.payload.getClaim(JwtSecurityConfig.CLAIM)
                if (claim != null) UserIdPrincipal(claim.toString()) else null
            }
        }
    }
}

fun Application.configureDatabase() {

    val properties by inject<ConfigProperties.Database>()

    DatabaseConfig.initialize(properties)
}