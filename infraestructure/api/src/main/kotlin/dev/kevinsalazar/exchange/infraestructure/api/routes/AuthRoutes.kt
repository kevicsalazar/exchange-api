package dev.kevinsalazar.exchange.infraestructure.api.routes

import dev.kevinsalazar.exchange.infraestructure.api.utils.respond
import dev.kevinsalazar.exchange.domain.payload.request.LoginRequest
import dev.kevinsalazar.exchange.domain.payload.request.RegisterRequest
import dev.kevinsalazar.exchange.domain.ports.driving.LoginUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Routing.authRoute() {

    val registerUseCase by inject<RegisterUseCase>()
    val loginUseCase by inject<LoginUseCase>()

    route("/auth") {
        post("/register") {
            val params = call.receive<RegisterRequest>()
            val result = registerUseCase.execute(params)
            call.respond(result = result)
        }
        post("/login") {
            val params = call.receive<LoginRequest>()
            val result = loginUseCase.execute(params)
            call.respond(result = result)
        }
    }
}
