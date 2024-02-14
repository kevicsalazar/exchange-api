package dev.kevinsalazar.exchange.server.config

import dev.kevinsalazar.exchange.adapters.api.di.apiModule
import dev.kevinsalazar.exchange.adapters.persist.config.DatabaseConfig
import dev.kevinsalazar.exchange.adapters.persist.persistModule
import dev.kevinsalazar.exchange.adapters.remote.remoteModule
import dev.kevinsalazar.exchange.domain.di.domainModule
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.server.di.configModule
import dev.kevinsalazar.exchange.server.utils.AppConfigProperties
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger(level = Level.INFO)
        modules(
            apiModule,
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

fun Application.configureDatabase() {

    val properties by inject<ConfigProperties.Database>()

    DatabaseConfig.initialize(properties)
}