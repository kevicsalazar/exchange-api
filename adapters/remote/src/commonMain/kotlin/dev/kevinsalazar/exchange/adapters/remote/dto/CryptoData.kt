package dev.kevinsalazar.exchange.adapters.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoData(
    val id: Int,
    val symbol: String,
    val name: String,
    val amount: Double,
    @SerialName("last_updated") val lastUpdated: String,
    val quote: Quote
) {
    @Serializable
    data class Quote(
        @SerialName("USD")
        val usd: CurrencyQuote
    ) {
        @Serializable
        data class CurrencyQuote(
            val price: Double,
            @SerialName("last_updated") val lastUpdated: String
        )
    }
}
