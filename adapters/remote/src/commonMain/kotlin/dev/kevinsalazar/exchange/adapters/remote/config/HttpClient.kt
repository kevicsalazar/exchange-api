package dev.kevinsalazar.exchange.adapters.remote.config


import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect fun httpClient(
    config: HttpClientConfig<*>.() -> Unit = {}
): HttpClient
