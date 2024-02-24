package dev.kevinsalazar.exchange.domain.payload.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String,
    val salt: String
)