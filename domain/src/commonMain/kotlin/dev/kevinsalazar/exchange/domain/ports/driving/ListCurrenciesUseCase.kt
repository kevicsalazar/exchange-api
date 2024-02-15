package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Currencies

interface ListCurrenciesUseCase {
    suspend fun execute(): Result<Currencies>
}