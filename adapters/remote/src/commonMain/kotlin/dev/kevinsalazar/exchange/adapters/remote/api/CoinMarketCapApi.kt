package dev.kevinsalazar.exchange.adapters.remote.api

import dev.kevinsalazar.exchange.adapters.remote.dto.CryptoData
import dev.kevinsalazar.exchange.adapters.remote.dto.CryptoInfo
import dev.kevinsalazar.exchange.adapters.remote.dto.Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class CoinMarketCapApi(
    private val client: HttpClient
) {

    suspend fun listCryptocurrencies() =
        client.get("/v1/cryptocurrency/listings/latest") {
            contentType(ContentType.Application.Json)
            parameter("start", 1)
            parameter("limit", 20)
            parameter("sort", "market_cap")
        }.body<Response<List<CryptoInfo>>>()

    suspend fun priceConvertion(amount: Number, id: String) =
        client.get("/v2/tools/price-conversion") {
            contentType(ContentType.Application.Json)
            parameter("amount", amount)
            parameter("id", id)
        }.body<Response<CryptoData>>()

}