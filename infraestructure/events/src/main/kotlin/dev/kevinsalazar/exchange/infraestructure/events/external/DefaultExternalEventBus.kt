package dev.kevinsalazar.exchange.infraestructure.events.external

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
import dev.kevinsalazar.exchange.domain.events.Event
import dev.kevinsalazar.exchange.domain.ports.driving.events.ExternalEventBus

class DefaultExternalEventBus : ExternalEventBus {

    override suspend fun publish(event: Event) {

        val sendRequest = SendMessageRequest {
            queueUrl = "queueUrlVal"
            messageBody = "message"
            delaySeconds = 10
        }

        SqsClient { region = "us-east-1" }.use { sqsClient ->
            sqsClient.sendMessage(sendRequest)
            println("A single message was successfully sent.")
        }
    }
}