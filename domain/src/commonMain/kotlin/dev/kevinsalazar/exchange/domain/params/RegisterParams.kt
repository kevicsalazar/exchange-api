package dev.kevinsalazar.exchange.domain.params

import dev.kevinsalazar.exchange.domain.utils.generateRandomSalt
import kotlinx.serialization.Serializable

@Serializable
data class RegisterParams(
    val name: String,
    val email: String,
    val password: String,
    val salt: String = generateRandomSalt()
)