package dev.kevinsalazar.exchange.adapters.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val exception: String,
    val message: String
)
