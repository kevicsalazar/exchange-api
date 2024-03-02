package dev.kevinsalazar.exchange.domain.events

data class SuccessfulSwapEvent(
    val userId: String,
    val transactionId: String
) : Event