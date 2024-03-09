package dev.kevinsalazar.exchange.infraestructure.remote.repository

import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository
import dev.kevinsalazar.exchange.domain.values.Convertion
import dev.kevinsalazar.exchange.infraestructure.remote.api.CurrencyApi
import dev.kevinsalazar.exchange.infraestructure.remote.mapper.CurrencyMapper

internal class DefaultExchangeRepository(
    private val api: CurrencyApi
) : ExchangeRepository {

    override suspend fun convert(amount: Double, send: String, receive: String): List<Convertion> {
        return api.convert(amount, send, receive)
            .flatMap(CurrencyMapper::mapToConvertion)
    }
}