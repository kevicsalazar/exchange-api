package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Currency

interface ListCurrenciesUseCase {
    suspend fun execute(): Result<List<Currency>>
}