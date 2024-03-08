package dev.kevinsalazar.exchange.infraestructure.persistence.config

import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.ports.driven.CurrencyRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.loadFromFile
import kotlinx.coroutines.runBlocking

class InitialDataLoader(
    private val currencyRepository: CurrencyRepository
) {

    fun loadInitialData() {
        runBlocking {
            loadCurrenciesFromFile()
        }
    }

    private suspend fun loadCurrenciesFromFile() {
        val path = "currencies.json"
        val currencies = loadFromFile<List<Currency>>(path) ?: return
        currencyRepository.save(currencies)
    }

}