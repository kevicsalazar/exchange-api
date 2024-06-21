package dev.kevinsalazar.exchange.domain.events

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisteredEvent(
    val userId: String,
    val name: String,
    val email: String
) : ExternalEvent
