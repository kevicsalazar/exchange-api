package dev.kevinsalazar.exchange.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String,
    val userId: String,
    val sentCurrencyCode: String? = null,
    val sentAmount: Float? = null,
    val receivedCurrencyCode: String? = null,
    val receivedAmount: Float? = null,
    val created: String
)