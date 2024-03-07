package dev.kevinsalazar.exchange.domain.events

data class SuccessfulSwapEvent(
    val transactionId: String
) : Event