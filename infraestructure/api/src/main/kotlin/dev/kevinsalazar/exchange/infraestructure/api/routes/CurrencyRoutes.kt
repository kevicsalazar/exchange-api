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

            val amount = call.request.queryParameters["amount"]?.toDouble()
            val send = call.request.queryParameters["send"]
            val receive = call.request.queryParameters["receive"]

            requireNotNull(amount) { "amount is required" }
            requireNotNull(send) { "send is required" }
            requireNotNull(receive) { "receive is required" }

            val result = convertUseCase.execute(amount, send, receive)
            call.respond(result = result)
        }
    }
}