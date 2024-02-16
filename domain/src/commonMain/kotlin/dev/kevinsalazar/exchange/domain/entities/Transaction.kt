package dev.kevinsalazar.exchange.domain.entities

import dev.kevinsalazar.exchange.domain.enums.Status
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String,
    val status: Status,
    val sentCurrencyId: Int,
    val sentAmount: Float,
    val receivedCurrencyId: Int,
    val receivedAmount: Float,
    val created: String
)