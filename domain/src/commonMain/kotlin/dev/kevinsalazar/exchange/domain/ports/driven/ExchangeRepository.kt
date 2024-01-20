package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.Exchange

interface ExchangeRepository {
    fun save(exchange: Exchange)
}