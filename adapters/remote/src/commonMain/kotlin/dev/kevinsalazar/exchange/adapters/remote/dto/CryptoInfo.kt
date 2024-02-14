package dev.kevinsalazar.exchange.adapters.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoInfo(
    val id: Int,
    val rank: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    @SerialName("is_active") val isActive: Int,
    @SerialName("first_historical_data") val firstHistoricalData: String,
    @SerialName("last_historical_data") val lastHistoricalData: String,
    val platform: String? = null
)
