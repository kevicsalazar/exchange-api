package dev.kevinsalazar.exchange.infraestructure.api.config

import dev.kevinsalazar.exchange.infraestructure.api.routes.authRoute
import dev.kevinsalazar.exchange.infraestructure.api.routes.currencyRoute
import dev.kevinsalazar.exchange.infraestructure.api.routes.exchangeRoute
import dev.kevinsalazar.exchange.infraestructure.api.routes.userRoute
import dev.kevinsalazar.exchange.infraestructure.api.utils.JwtSecurityConfig
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject


fun Application.configureRoutes() {
    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = false
        })
    }
    routing {
        authRoute()
        authenticate {
            currencyRoute()
            exchangeRoute()
            userRoute()
        }
    }
}

fun Application.configureAuthentication() {

    val jwtSecurityConfig by inject<JwtSecurityConfig>()

    install(Authentication) {
        jwt {
            verifier(jwtSecurityConfig.verifier)
            validate {
                val claim = it.payload.getClaim(JwtSecurityConfig.CLAIM)
                if (claim != null) UserIdPrincipal(claim.toString()) else null
            }
        }
    }
}