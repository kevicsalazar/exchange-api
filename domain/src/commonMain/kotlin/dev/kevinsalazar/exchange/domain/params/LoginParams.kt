package dev.kevinsalazar.exchange.domain.params

import kotlinx.serialization.Serializable

@Serializable
data class LoginParams(
    val email: String,
    val password: String
)