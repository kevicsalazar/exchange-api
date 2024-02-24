package dev.kevinsalazar.exchange.domain.payload.request

data class WithdrawalRequest(
    val currencyId: Int,
    val amount: Float
)