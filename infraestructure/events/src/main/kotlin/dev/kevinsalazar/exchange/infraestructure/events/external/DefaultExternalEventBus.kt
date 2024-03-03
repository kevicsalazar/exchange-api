package dev.kevinsalazar.exchange.infraestructure.events.external

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
import dev.kevinsalazar.exchange.domain.enums.Queue
import dev.kevinsalazar.exchange.domain.events.Event
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.domain.ports.driven.events.ExternalEventBus
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DefaultExternalEventBus(
    private val props: ConfigProperties.Events
) : ExternalEventBus {

    private val queues by lazy {
        props.queues.associateBy({ it.name }, { it.url })
    }

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        encodeDefaults = false
    }

    override suspend fun publish(event: Event, queue: Queue) {

        val sendRequest = SendMessageRequest {
            queueUrl = queues[queue.name]
            messageBody = json.encodeToString(event)
            delaySeconds = 10
        }

        SqsClient { region = props.region }.use { client ->
            client.sendMessage(sendRequest)
            println("A message was successfully sent.")
        }
    }
}