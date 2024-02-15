package dev.kevinsalazar.exchange.domain.entities

import dev.kevinsalazar.exchange.domain.enums.Status
import kotlinx.serialization.Serializable

@Serializable
data class Exchange(
    val id: String,
    val userId: String,
    val status: Status,
    val sentCurrency: Currency,
    val sentAmount: Float,
    val receivedCurrency: Currency,
    val receivedAmount: Float
)
