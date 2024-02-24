package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Convertion
import dev.kevinsalazar.exchange.domain.ports.driven.CurrenciesRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ConvertUseCase

class DefaultConvertUseCase(
    private val currenciesRepository: CurrenciesRepository
) : ConvertUseCase {
    override suspend fun execute(amount: Float, from: Int, to: Int): Result<Convertion> {
        try {
            val data = currenciesRepository.convert(amount, from, to)
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}