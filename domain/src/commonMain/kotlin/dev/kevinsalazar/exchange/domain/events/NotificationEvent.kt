package dev.kevinsalazar.exchange.domain.events

data class NotificationEvent(
    val email: String,
    val message: String
)