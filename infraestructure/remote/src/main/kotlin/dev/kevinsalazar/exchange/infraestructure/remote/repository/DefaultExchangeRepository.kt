package dev.kevinsalazar.exchange.infraestructure.remote.repository

import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository
import dev.kevinsalazar.exchange.domain.values.Convertion
import dev.kevinsalazar.exchange.infraestructure.remote.api.CurrencyApi
import dev.kevinsalazar.exchange.infraestructure.remote.mapper.CurrencyMapper

internal class DefaultExchangeRepository(
    private val api: CurrencyApi
) : ExchangeRepository {

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