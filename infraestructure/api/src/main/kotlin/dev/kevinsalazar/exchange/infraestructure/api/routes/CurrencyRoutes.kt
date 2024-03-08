package dev.kevinsalazar.exchange.infraestructure.api.routes

import dev.kevinsalazar.exchange.domain.ports.driving.ConvertUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.ListCurrenciesUseCase
import dev.kevinsalazar.exchange.infraestructure.api.utils.respond
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.currencyRoute() {

    val listCurrenciesUseCase by inject<ListCurrenciesUseCase>()
    val convertUseCase by inject<ConvertUseCase>()

    route("currency") {
        get("/list") {
            val result = listCurrenciesUseCase.execute()
            call.respond(result = result)
        }
        get("/convert") {

            val amount = call.request.queryParameters["amount"]?.toFloat()
            val from = call.request.queryParameters["from"]
            val to = call.request.queryParameters["to"]

            requireNotNull(amount) { "amount is required" }
            requireNotNull(from) { "from is required" }
            requireNotNull(to) { "to is required" }

            val result = convertUseCase.execute(amount, from, to)
            call.respond(result = result)
        }
    }
}