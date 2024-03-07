package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.values.Convertion
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ConvertUseCase

class DefaultConvertUseCase(
    private val exchangeRepository: ExchangeRepository
) : ConvertUseCase {
    override suspend fun execute(amount: Float, from: Int, to: Int): Result<Convertion> {
        try {
            val data = exchangeRepository.convert(amount, from, to)
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}