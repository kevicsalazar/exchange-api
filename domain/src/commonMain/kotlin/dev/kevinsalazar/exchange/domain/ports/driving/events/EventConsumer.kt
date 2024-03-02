package dev.kevinsalazar.exchange.domain.ports.driving.events

interface EventConsumer {
    fun start()
    fun stop()
}