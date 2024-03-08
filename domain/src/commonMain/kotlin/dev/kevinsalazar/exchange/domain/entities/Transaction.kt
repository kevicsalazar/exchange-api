package dev.kevinsalazar.exchange.domain.entities

import dev.kevinsalazar.exchange.domain.enums.Status
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String,
    val userId: String,
    val status: Status,
    val sentCurrencyCode: String? = null,
    val sentAmount: Float? = null,
    val receivedCurrencyCode: String? = null,
    val receivedAmount: Float? = null,
    val created: String
)