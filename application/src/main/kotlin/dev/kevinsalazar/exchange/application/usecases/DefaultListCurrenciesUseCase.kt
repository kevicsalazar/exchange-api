package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.ports.driven.CurrencyRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ListCurrenciesUseCase

internal class DefaultListCurrenciesUseCase(
    private val currencyRepository: CurrencyRepository
) : ListCurrenciesUseCase {

    override suspend fun execute(): Result<List<Currency>> {
        try {
            val data = currencyRepository.findAll()
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}