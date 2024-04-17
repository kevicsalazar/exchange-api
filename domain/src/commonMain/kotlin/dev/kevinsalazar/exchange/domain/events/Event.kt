package dev.kevinsalazar.exchange.domain.events

import kotlinx.serialization.Serializable

@Serializable
sealed interface Event

@Serializable
sealed interface InternalEvent : Event

@Serializable
sealed interface ExternalEvent : Event