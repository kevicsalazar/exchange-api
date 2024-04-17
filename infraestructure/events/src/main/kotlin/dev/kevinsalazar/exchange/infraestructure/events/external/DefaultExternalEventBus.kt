package dev.kevinsalazar.exchange.infraestructure.events.external

import aws.sdk.kotlin.services.eventbridge.EventBridgeClient
import aws.sdk.kotlin.services.eventbridge.model.PutEventsRequest
import aws.sdk.kotlin.services.eventbridge.model.PutEventsRequestEntry
import dev.kevinsalazar.exchange.domain.events.ExternalEvent
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.domain.ports.driven.events.ExternalEventBus
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DefaultExternalEventBus(
    private val props: ConfigProperties.Events
) : ExternalEventBus {

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        encodeDefaults = false
    }

    override suspend fun publish(event: ExternalEvent) {

        val body = json.encodeToString(event)

        val entry = PutEventsRequestEntry {
            source = props.source
            detail = body
            detailType = event.javaClass.simpleName
        }

        val eventsRequest = PutEventsRequest {
            entries = listOf(entry)
        }

        EventBridgeClient { region = props.region }.use { client ->
            client.putEvents(eventsRequest)
        }
    }
}