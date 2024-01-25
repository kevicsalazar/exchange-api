package dev.kevinsalazar.exchange.adapters.api.routes

import dev.kevinsalazar.exchange.adapters.api.utils.respond
import dev.kevinsalazar.exchange.domain.params.LoginParams
import dev.kevinsalazar.exchange.domain.params.RegisterParams
import dev.kevinsalazar.exchange.domain.ports.driving.LoginUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Routing.authRoute() {

    val registerUseCase by inject<RegisterUseCase>()
    val loginUseCase by inject<LoginUseCase>()

    route("/auth") {
        post("/register") {
            val params = call.receive<RegisterParams>()
            val result = registerUseCase.execute(params)
            call.respond(result = result)
        }
        post("/login") {
            val params = call.receive<LoginParams>()
            val result = loginUseCase.execute(params)
            call.respond(result = result)
        }
    }
}
