package dev.kevinsalazar.exchange.adapters.api.routes

import dev.kevinsalazar.exchange.domain.ports.driving.ExchangeRateUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Routing.exchangeRoute() {
    val exchangeRateUseCase by inject<ExchangeRateUseCase>()

    get("/exchange/rate") {
        call.respond(
            status = HttpStatusCode.OK,
            message = exchangeRateUseCase.hashCode(),
        )
    }
}