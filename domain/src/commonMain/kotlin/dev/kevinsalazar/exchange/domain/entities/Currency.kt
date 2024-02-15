package dev.kevinsalazar.exchange.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val id: Int,
    val rank: Int? = null,
    val name: String,
    val symbol: String,
    val sign: String? = null
)