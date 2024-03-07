package dev.kevinsalazar.exchange.domain.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailEvent(
    @SerialName("template") val template: String,
    @SerialName("destination") val destination: String,
    @SerialName("data") val data: String,
) : Event