package dev.kevinsalazar.exchange.adapters.api.config

import dev.kevinsalazar.exchange.adapters.api.routes.exchangeRoute
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun Application.config() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        exchangeRoute()
    }
}