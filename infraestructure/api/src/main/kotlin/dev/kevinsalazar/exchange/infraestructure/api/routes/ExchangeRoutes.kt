package dev.kevinsalazar.exchange.infraestructure.api.routes

import dev.kevinsalazar.exchange.domain.payload.request.SwapRequest
import dev.kevinsalazar.exchange.domain.ports.driving.SwapUseCase
import dev.kevinsalazar.exchange.infraestructure.api.utils.respond
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.exchangeRoute() {

    val swapUseCase by inject<SwapUseCase>()

    route("exchange") {
        post("/swap") {
            val userId = requireNotNull(call.principal<UserIdPrincipal>()?.name)
            val params = call.receive<SwapRequest>()
            val result = swapUseCase.execute(userId, params)
            call.respond(result = result)
        }
    }
}