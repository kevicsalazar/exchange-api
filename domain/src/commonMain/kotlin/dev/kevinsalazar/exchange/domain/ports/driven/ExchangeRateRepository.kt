package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.ExchangeRate

interface ExchangeRateRepository {
    fun find(): ExchangeRate
}