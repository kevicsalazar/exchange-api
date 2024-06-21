package dev.kevinsalazar.exchange.domain.events

import kotlinx.serialization.Serializable

@Serializable
data class SuccessfulSwapEvent(
    val transactionId: String,
    val userId: String,
    val sentCurrencyCode: String? = null,
    val sentAmount: Double? = null,
    val receivedCurrencyCode: String? = null,
    val receivedAmount: Double? = null,
    val created: String
) : ExternalEvent