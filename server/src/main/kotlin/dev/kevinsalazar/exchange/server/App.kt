package dev.kevinsalazar.exchange.server

import dev.kevinsalazar.exchange.adapters.api.config.config
import dev.kevinsalazar.exchange.adapters.persist.persistModule
import dev.kevinsalazar.exchange.adapters.remote.remoteModule
import dev.kevinsalazar.exchange.domain.domainModule
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.core.logger.Level
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    return EngineMain.main(args)
}

fun Application.main() {
    install(Koin) {
        slf4jLogger(level = Level.INFO)
        modules(
            persistModule,
            remoteModule,
            domainModule,
        )
    }
    config()
}
