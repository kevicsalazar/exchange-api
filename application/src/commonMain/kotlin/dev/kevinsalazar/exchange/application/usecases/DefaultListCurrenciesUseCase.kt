package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Currencies
import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.enums.CurrencySymbol
import dev.kevinsalazar.exchange.domain.ports.driven.CurrenciesRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ListCurrenciesUseCase

internal class DefaultListCurrenciesUseCase(
    private val currenciesRepository: CurrenciesRepository
) : ListCurrenciesUseCase {

    private val symbols by lazy { CurrencySymbol.entries.map { it.name } }
    override suspend fun execute(): Result<Currencies> {
        try {
            val data = Currencies(findCrypto(), findFiat())
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private suspend fun findCrypto(): List<Currency> {
        return currenciesRepository.findCrypto()
            .filter { it.symbol in symbols }
    }

    private suspend fun findFiat(): List<Currency> {
        return currenciesRepository.findFiat()
            .filter { it.symbol in symbols }
    }

}