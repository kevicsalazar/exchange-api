package dev.kevinsalazar.exchange.infraestructure.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FiatInfo(
    val id: Int,
    val name: String,
    val sign: String,
    val symbol: String
)