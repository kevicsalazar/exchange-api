package dev.kevinsalazar.exchange.infraestructure.remote.api

import dev.kevinsalazar.exchange.infraestructure.remote.dto.CryptoConvertion
import dev.kevinsalazar.exchange.infraestructure.remote.dto.Response
import dev.kevinsalazar.exchange.infraestructure.remote.utils.get
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class CurrencyApi(
    private val client: HttpClient
) {

    suspend fun convert(amount: Float, from: String, to: String) =
        client.get("/v2/tools/price-conversion") {
            header(HttpHeaders.CacheControl, CacheControl.MaxAge(60))
            contentType(ContentType.Application.Json)
            parameter("amount", amount)
            parameter("symbol", from)
            parameter("convert", to)
        }.body<Response<CryptoConvertion>>().get()

}