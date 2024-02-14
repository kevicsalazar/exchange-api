package dev.kevinsalazar.exchange.server

import dev.kevinsalazar.exchange.adapters.api.config.configureAuthentication
import dev.kevinsalazar.exchange.adapters.api.config.configureRoutes
import dev.kevinsalazar.exchange.server.config.configureDatabase
import dev.kevinsalazar.exchange.server.config.configureKoin
import dev.kevinsalazar.exchange.server.config.configureProperties
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    return EngineMain.main(args)
}

fun Application.main() {
    configureKoin()
    configureProperties()
    configureAuthentication()
    configureRoutes()
    configureDatabase()
}
