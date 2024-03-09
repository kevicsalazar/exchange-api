package dev.kevinsalazar.exchange.domain.values

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Convertion(
    val sentCurrencyCode: String,
    @Transient
    val sentCurrencyName: String = "",
    val sentAmount: Double,
    val receivedCurrencyCode: String,
    val receivedAmount: Double
)