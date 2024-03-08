package dev.kevinsalazar.exchange.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val code: String,
    val name: String,
    val locale: String,
)