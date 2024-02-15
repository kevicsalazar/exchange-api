package dev.kevinsalazar.exchange.adapters.remote.repository

import dev.kevinsalazar.exchange.adapters.remote.api.CurrencyApi
import dev.kevinsalazar.exchange.adapters.remote.mapper.CurrencyMapper
import dev.kevinsalazar.exchange.domain.entities.Convertion
import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.ports.driven.CurrenciesRepository

internal class DefaultCurrenciesRepository(
    private val api: CurrencyApi
) : CurrenciesRepository {

    override suspend fun findCrypto(): List<Currency> {
        val response = api.listCrypto()
        return CurrencyMapper.mapCrypto(response)
    }

    override suspend fun findFiat(): List<Currency> {
        val response = api.listFiat()
        return CurrencyMapper.mapFiat(response)
    }

    override suspend fun convert(amount: Float, from: Int, to: Int): Convertion {
        val response = api.convert(amount, from, to)
        return CurrencyMapper.mapToConvertion(response)
    }
}