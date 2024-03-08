package dev.kevinsalazar.exchange.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Balance(
    val id: String,
    val userId: String,
    val amount: Float,
    val currencyCode: String
)