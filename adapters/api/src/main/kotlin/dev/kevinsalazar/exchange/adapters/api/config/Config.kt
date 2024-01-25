package dev.kevinsalazar.exchange.adapters.api.config

import dev.kevinsalazar.exchange.adapters.api.routes.authRoute
import dev.kevinsalazar.exchange.adapters.api.routes.exchangeRoute
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*


fun Application.configureRoutes() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        authRoute()
        authenticate {
            exchangeRoute()
        }
    }
}