package dev.kevinsalazar.exchange.domain.ports.driven.events

interface EventConsumer {
    fun start()
    fun stop()
}