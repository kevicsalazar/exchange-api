package dev.kevinsalazar.exchange.infraestructure.remote.mapper

import dev.kevinsalazar.exchange.domain.values.Convertion
import dev.kevinsalazar.exchange.infraestructure.remote.dto.CurrencyConvertion

object CurrencyMapper {

    fun mapToConvertion(data: CurrencyConvertion): List<Convertion> {
        return data.quote.toList()
            .map { (symbol, quote) ->
                Convertion(
                    sentCurrencyName = data.name,
                    sentCurrencyCode = data.symbol,
                    sentAmount = data.amount,
                    receivedCurrencyCode = symbol,
                    receivedAmount = quote.price ?: 0.0,
                )
            }
    }
}
