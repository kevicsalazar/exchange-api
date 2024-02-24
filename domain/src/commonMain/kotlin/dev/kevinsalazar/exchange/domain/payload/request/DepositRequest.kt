package dev.kevinsalazar.exchange.domain.payload.request

data class DepositRequest(
    val currencyId: Int,
    val amount: Float
)