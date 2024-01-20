package dev.kevinsalazar.exchange.repository

import dev.kevinsalazar.exchange.domain.entities.Exchange
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository

internal class DefaultExchangeRepository: ExchangeRepository {
    override fun save(exchange: Exchange) {
        TODO("Not yet implemented")
    }
}