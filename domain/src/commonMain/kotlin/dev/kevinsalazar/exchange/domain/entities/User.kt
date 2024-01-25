package dev.kevinsalazar.exchange.domain.entities

import dev.kevinsalazar.exchange.domain.utils.generateUUID
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String = generateUUID(),
    val name: String,
    val email: String,
    var authToken: String? = null
)
