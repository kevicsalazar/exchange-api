package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.Convertion
import dev.kevinsalazar.exchange.domain.entities.Currency

interface CurrenciesRepository {
    suspend fun findCrypto(): List<Currency>
    suspend fun findFiat(): List<Currency>
    suspend fun convert(amount: Float, from: Int, to: Int): Convertion
}