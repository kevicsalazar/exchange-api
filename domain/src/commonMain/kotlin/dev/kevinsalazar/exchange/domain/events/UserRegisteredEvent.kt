package dev.kevinsalazar.exchange.domain.events

data class UserRegisteredEvent(
    val userId: String,
    val email: String
) : Event