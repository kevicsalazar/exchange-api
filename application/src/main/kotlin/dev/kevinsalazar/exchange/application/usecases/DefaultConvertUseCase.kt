package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.ports.driven.CurrencyRepository
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ConvertUseCase
import dev.kevinsalazar.exchange.domain.values.Convertion

class DefaultConvertUseCase(
    private val exchangeRepository: ExchangeRepository,
    private val currencyRepository: CurrencyRepository
) : ConvertUseCase {
    override suspend fun execute(amount: Double, send: String, receive: String): Result<Convertion> {
        try {
            val convertions = exchangeRepository.convert(amount, send, receive)
            if (convertions.size == 1) {
                val convertion = convertions.first()
                return Result.success(convertion)
            } else {
                val convertion = filterConvertion(convertions, send)
                return Result.success(convertion)
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private suspend fun filterConvertion(convertions: List<Convertion>, send: String): Convertion {
        val sendCurrencyName = getCurrencyName(send)
        return convertions.first { it.sentCurrencyName == sendCurrencyName }
    }

    private suspend fun getCurrencyName(code: String): String {
        return requireNotNull(currencyRepository.findByCode(code)).name
    }

}