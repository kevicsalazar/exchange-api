package dev.kevinsalazar.exchange.domain.values

import kotlinx.serialization.Serializable

@Serializable
data class WelcomeEmail(
    val name: String
)