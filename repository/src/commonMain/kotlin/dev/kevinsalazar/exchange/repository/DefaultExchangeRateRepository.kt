package dev.kevinsalazar.exchange.repository

import dev.kevinsalazar.exchange.domain.entities.ExchangeRate
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRateRepository

internal class DefaultExchangeRateRepository: ExchangeRateRepository {
    override fun find(): ExchangeRate {
        TODO("Not yet implemented")
    }
}