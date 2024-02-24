package dev.kevinsalazar.exchange.domain.payload.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)