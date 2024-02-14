package dev.kevinsalazar.exchange.adapters.remote.config

import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.plugins.cache.*

actual fun httpClient(
    config: HttpClientConfig<*>.() -> Unit,
) = HttpClient(Apache5) {
    config(this)
    engine {
        followRedirects = true
        socketTimeout = 10_000
        connectTimeout = 10_000
        connectionRequestTimeout = 20_000
    }
    install(HttpCache)
}

