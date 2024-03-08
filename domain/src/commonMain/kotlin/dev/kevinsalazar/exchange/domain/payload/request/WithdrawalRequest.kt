package dev.kevinsalazar.exchange.domain.payload.request

import kotlinx.serialization.Serializable

@Serializable
data class WithdrawalRequest(
    val currencyCode: String,
    val amount: Float
)