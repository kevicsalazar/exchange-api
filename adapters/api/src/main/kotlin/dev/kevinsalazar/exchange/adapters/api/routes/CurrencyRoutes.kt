package dev.kevinsalazar.exchange.adapters.api.routes

import dev.kevinsalazar.exchange.adapters.api.utils.respond
import dev.kevinsalazar.exchange.domain.ports.driving.ConvertCurrencyUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.ListCurrenciesUseCase
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.currencyRoute() {

    val listCurrenciesUseCase by inject<ListCurrenciesUseCase>()
    val convertCurrencyUseCase by inject<ConvertCurrencyUseCase>()

    route("currency") {
        get("/list") {
            val result = listCurrenciesUseCase.execute()
            call.respond(result = result)
        }
        get("/convert") {

            val amount = call.request.queryParameters["amount"]?.toFloat()
            val from = call.request.queryParameters["from"]?.toInt()
            val to = call.request.queryParameters["to"]?.toInt()

            requireNotNull(amount) { "amount is required" }
            requireNotNull(from) { "from is required" }
            requireNotNull(to) { "to is required" }

            val result = convertCurrencyUseCase.execute(amount, from, to)
            call.respond(result = result)
        }
    }
}