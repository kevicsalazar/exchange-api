package dev.kevinsalazar.exchange.adapters.api.routes

import dev.kevinsalazar.exchange.adapters.api.utils.respond
import dev.kevinsalazar.exchange.domain.params.SwapParams
import dev.kevinsalazar.exchange.domain.ports.driving.SwapUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.exchangeRoute() {

    val swapUseCase by inject<SwapUseCase>()

    route("exchange") {
        get("/swap") {
            val userId = requireNotNull(call.principal<UserIdPrincipal>()?.name)
            val params = call.receive<SwapParams>()
            val result = swapUseCase.execute(userId, params)
            call.respond(result = result)
        }
    }
}