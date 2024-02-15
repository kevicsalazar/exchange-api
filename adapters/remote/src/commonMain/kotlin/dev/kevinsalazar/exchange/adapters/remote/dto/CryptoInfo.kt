package dev.kevinsalazar.exchange.adapters.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoInfo(
    val id: Int,
    val name: String,
    val symbol: String,
    @SerialName("cmc_rank")
    val rank: Int,
    @SerialName("last_updated")
    val lastUpdated: String,
)