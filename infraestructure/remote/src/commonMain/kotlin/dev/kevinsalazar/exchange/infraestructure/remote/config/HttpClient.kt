package dev.kevinsalazar.exchange.infraestructure.remote.config


import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect fun httpClient(
    config: HttpClientConfig<*>.() -> Unit = {}
): HttpClient
