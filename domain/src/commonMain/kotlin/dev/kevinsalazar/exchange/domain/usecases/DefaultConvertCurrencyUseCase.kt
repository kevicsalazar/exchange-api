package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.Convertion
import dev.kevinsalazar.exchange.domain.ports.driven.CurrenciesRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ConvertCurrencyUseCase

class DefaultConvertCurrencyUseCase(
    private val currenciesRepository: CurrenciesRepository
) : ConvertCurrencyUseCase {
    override suspend fun execute(amount: Float, from: Int, to: Int): Result<Convertion> {
        try {
            val data = currenciesRepository.convert(amount, from, to)
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}