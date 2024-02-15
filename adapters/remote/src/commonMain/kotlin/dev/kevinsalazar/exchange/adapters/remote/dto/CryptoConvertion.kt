package dev.kevinsalazar.exchange.adapters.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoConvertion(
    val id: Int,
    val symbol: String,
    val name: String,
    val amount: Float,
    @SerialName("last_updated")
    val lastUpdated: String,
    val quote: HashMap<String, CurrencyQuote>
) {

    @Serializable
    data class CurrencyQuote(
        val price: Float,
        @SerialName("last_updated")
        val lastUpdated: String
    )
}
