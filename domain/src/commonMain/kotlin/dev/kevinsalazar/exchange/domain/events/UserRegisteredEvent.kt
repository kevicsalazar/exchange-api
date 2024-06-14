package dev.kevinsalazar.exchange.domain.events

data class UserRegisteredEvent(
    val userId: String,
    val name: String,
    val email: String
) : ExternalEvent