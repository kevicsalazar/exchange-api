package dev.kevinsalazar.exchange.infraestructure.remote.config

import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal class RemoteConfig(
    private val properties: ConfigProperties.Remote
) {

    fun get() = httpClient {
        defaultRequest {
            url(properties.url.encodeURLPath())
            header("X-CMC_PRO_API_KEY", properties.apiKey)
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                encodeDefaults = false
            })
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}