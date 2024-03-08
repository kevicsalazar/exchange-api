package dev.kevinsalazar.exchange.infraestructure.remote.repository

import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository
import dev.kevinsalazar.exchange.domain.values.Convertion
import dev.kevinsalazar.exchange.infraestructure.remote.api.CurrencyApi
import dev.kevinsalazar.exchange.infraestructure.remote.mapper.CurrencyMapper

internal class DefaultExchangeRepository(
    private val api: CurrencyApi
) : ExchangeRepository {

    override suspend fun convert(amount: Float, from: String, to: String): Convertion {
        val response = api.convert(amount, from, to)
        return CurrencyMapper.mapToConvertion(response)
    }
}