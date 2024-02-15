package dev.kevinsalazar.exchange.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Convertion(
    val sentId: Int,
    val sentAmount: Float,
    val receivedId: Int,
    val receivedAmount: Float,
    val lastUpdated: String
)