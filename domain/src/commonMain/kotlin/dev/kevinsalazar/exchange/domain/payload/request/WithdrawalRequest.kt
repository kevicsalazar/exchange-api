package dev.kevinsalazar.exchange.domain.payload.request

import kotlinx.serialization.Serializable

@Serializable
data class WithdrawalRequest(
    val currencyId: Int,
    val amount: Float
)