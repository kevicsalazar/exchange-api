package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.Currency

interface CurrencyRepository {
    suspend fun save(currencies: List<Currency>)
    suspend fun findAll(): List<Currency>
    suspend fun findByCode(code: String): Currency?
}