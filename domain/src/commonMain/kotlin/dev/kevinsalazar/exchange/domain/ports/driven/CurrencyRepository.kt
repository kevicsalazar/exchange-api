package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.Currency

interface CurrencyRepository {

    suspend fun save(currencies: Set<Currency>)
    suspend fun findById(id: Int): Currency?
}