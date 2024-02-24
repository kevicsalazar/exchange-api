package dev.kevinsalazar.exchange.domain.payload.request

import kotlinx.serialization.Serializable

@Serializable
data class DepositRequest(
    val currencyId: Int,
    val amount: Float
)