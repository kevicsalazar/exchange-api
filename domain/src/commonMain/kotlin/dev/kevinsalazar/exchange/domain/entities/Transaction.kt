package dev.kevinsalazar.exchange.domain.entities

import dev.kevinsalazar.exchange.domain.enums.Status
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String,
    val userId: String,
    val status: Status,
    val sentCurrencyId: Int? = null,
    val sentAmount: Float? = null,
    val receivedCurrencyId: Int? = null,
    val receivedAmount: Float? = null,
    val created: String
)