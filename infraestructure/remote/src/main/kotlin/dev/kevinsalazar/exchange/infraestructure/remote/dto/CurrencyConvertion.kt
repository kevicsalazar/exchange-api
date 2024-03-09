package dev.kevinsalazar.exchange.infraestructure.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyConvertion(
    val id: Int,
    val symbol: String,
    val name: String,
    val amount: Double,
    val quote: HashMap<String, CurrencyQuote>
) {

    @Serializable
    data class CurrencyQuote(
        val price: Double? = null
    )
}
