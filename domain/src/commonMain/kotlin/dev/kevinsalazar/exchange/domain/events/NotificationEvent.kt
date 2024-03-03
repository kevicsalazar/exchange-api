package dev.kevinsalazar.exchange.domain.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationEvent(
    @SerialName("email") val email: Email? = null
) : Event {
    @Serializable
    data class Email(
        @SerialName("email") val email: String,
        @SerialName("message") val message: String,
    )

}
