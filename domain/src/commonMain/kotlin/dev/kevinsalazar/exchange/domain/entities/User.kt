package dev.kevinsalazar.exchange.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    var authToken: String? = null
)
