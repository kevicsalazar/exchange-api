package dev.kevinsalazar.exchange.adapters.remote.repository

import dev.kevinsalazar.exchange.adapters.remote.api.CoinMarketCapApi
import dev.kevinsalazar.exchange.domain.entities.ExchangeRate
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRateRepository

internal class DefaultExchangeRateRepository(
    private val api: CoinMarketCapApi
): ExchangeRateRepository {
    override fun find(): ExchangeRate {
        TODO("Not yet implemented")
    }
}