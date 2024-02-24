package dev.kevinsalazar.exchange.infraestructure.api.routes

import dev.kevinsalazar.exchange.domain.payload.request.DepositRequest
import dev.kevinsalazar.exchange.domain.payload.request.WithdrawalRequest
import dev.kevinsalazar.exchange.domain.ports.driving.DepositUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.ListTransactionsUseCase
import dev.kevinsalazar.exchange.domain.ports.driving.WithdrawalUseCase
import dev.kevinsalazar.exchange.infraestructure.api.utils.respond
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.userRoute() {

    val depositUseCase by inject<DepositUseCase>()
    val withdrawalUseCase by inject<WithdrawalUseCase>()
    val listTransactionsUseCase by inject<ListTransactionsUseCase>()

    route("user") {
        post("/deposit") {
            val userId = requireNotNull(call.principal<UserIdPrincipal>()?.name)
            val params = call.receive<DepositRequest>()
            val result = depositUseCase.execute(userId, params)
            call.respond(result = result)
        }
        post("/withdrawal") {
            val userId = requireNotNull(call.principal<UserIdPrincipal>()?.name)
            val params = call.receive<WithdrawalRequest>()
            val result = withdrawalUseCase.execute(userId, params)
            call.respond(result = result)
        }
        get("/transactions") {
            val userId = requireNotNull(call.principal<UserIdPrincipal>()?.name)
            val result = listTransactionsUseCase.execute(userId)
            call.respond(result = result)
        }
    }
}