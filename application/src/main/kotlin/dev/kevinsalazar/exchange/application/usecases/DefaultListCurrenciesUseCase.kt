package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.enums.CurrencySymbol
import dev.kevinsalazar.exchange.domain.ports.driven.CurrencyRepository
import dev.kevinsalazar.exchange.domain.ports.driven.ExchangeRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ListCurrenciesUseCase
import dev.kevinsalazar.exchange.domain.values.Currencies

internal class DefaultListCurrenciesUseCase(
    private val exchangeRepository: ExchangeRepository,
    private val currencyRepository: CurrencyRepository
) : ListCurrenciesUseCase {

    private val symbols by lazy { CurrencySymbol.entries.map { it.name } }
    override suspend fun execute(): Result<Currencies> {
        try {
            val cryptos = findCrypto()
            val fiat = findFiat()
            currencyRepository.save(cryptos union fiat)
            val data = Currencies(cryptos, fiat)
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private suspend fun findCrypto(): List<Currency> {
        return exchangeRepository.findCrypto()
            .filter { it.symbol in symbols }
    }

    private suspend fun findFiat(): List<Currency> {
        return exchangeRepository.findFiat()
            .filter { it.symbol in symbols }
    }

}