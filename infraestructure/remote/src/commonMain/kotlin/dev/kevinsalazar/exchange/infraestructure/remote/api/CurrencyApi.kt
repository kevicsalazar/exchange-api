package dev.kevinsalazar.exchange.infraestructure.remote.api

import dev.kevinsalazar.exchange.infraestructure.remote.dto.CryptoConvertion
import dev.kevinsalazar.exchange.infraestructure.remote.dto.CryptoInfo
import dev.kevinsalazar.exchange.infraestructure.remote.dto.FiatInfo
import dev.kevinsalazar.exchange.infraestructure.remote.dto.Response
import dev.kevinsalazar.exchange.infraestructure.remote.utils.get
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class CurrencyApi(
    private val client: HttpClient
) {

    suspend fun listCrypto() =
        client.get("/v1/cryptocurrency/listings/latest") {
            header(HttpHeaders.CacheControl, CacheControl.MaxAge(3600))
            contentType(ContentType.Application.Json)
            parameter("start", 1)
            parameter("limit", 25)
            parameter("sort", "market_cap")
        }.body<Response<List<CryptoInfo>>>().get()

    suspend fun listFiat() =
        client.get("/v1/fiat/map") {
            header(HttpHeaders.CacheControl, CacheControl.MaxAge(3600))
            contentType(ContentType.Application.Json)
            parameter("start", 1)
            parameter("limit", 100)
            parameter("sort", "id")
        }.body<Response<List<FiatInfo>>>().get()

    suspend fun convert(amount: Float, from: Int, to: Int) =
        client.get("/v2/tools/price-conversion") {
            header(HttpHeaders.CacheControl, CacheControl.MaxAge(60))
            contentType(ContentType.Application.Json)
            parameter("amount", amount)
            parameter("id", from)
            parameter("convert_id", to)
        }.body<Response<CryptoConvertion>>().get()

}