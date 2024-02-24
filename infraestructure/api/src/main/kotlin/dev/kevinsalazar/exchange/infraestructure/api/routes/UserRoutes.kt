package dev.kevinsalazar.exchange.infraestructure.api.routes

import dev.kevinsalazar.exchange.domain.payload.request.DepositRequest
import dev.kevinsalazar.exchange.domain.ports.driving.DepositUseCase
import dev.kevinsalazar.exchange.infraestructure.api.utils.respond
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.userRoute() {

    val depositUseCase by inject<DepositUseCase>()

    route("user") {
        get("/deposit") {
            val userId = requireNotNull(call.principal<UserIdPrincipal>()?.name)
            val params = call.receive<DepositRequest>()
            val result = depositUseCase.execute(userId, params)
            call.respond(result = result)
        }
    }
}