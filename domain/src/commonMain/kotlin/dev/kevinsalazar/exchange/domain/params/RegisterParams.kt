package dev.kevinsalazar.exchange.domain.params

import kotlinx.serialization.Serializable

@Serializable
data class RegisterParams(
    val name: String,
    val email: String,
    val password: String
)