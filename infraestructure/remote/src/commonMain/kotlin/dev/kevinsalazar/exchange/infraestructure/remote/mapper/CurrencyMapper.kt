package dev.kevinsalazar.exchange.infraestructure.remote.mapper

import dev.kevinsalazar.exchange.infraestructure.remote.dto.CryptoConvertion
import dev.kevinsalazar.exchange.infraestructure.remote.dto.CryptoInfo
import dev.kevinsalazar.exchange.infraestructure.remote.dto.FiatInfo
import dev.kevinsalazar.exchange.domain.entities.Convertion
import dev.kevinsalazar.exchange.domain.entities.Currency

object CurrencyMapper {

    fun mapCrypto(data: List<CryptoInfo>): List<Currency> {
        return data.map {
            Currency(
                id = it.id,
                rank = it.rank,
                name = it.name,
                symbol = it.symbol,
            )
        }
    }

    fun mapFiat(data: List<FiatInfo>): List<Currency> {
        return data.map {
            Currency(
                id = it.id,
                sign = it.sign,
                name = it.name,
                symbol = it.symbol,
            )
        }
    }

    fun mapToConvertion(data: CryptoConvertion): Convertion {

        val (id, quote) = data.quote.toList().first()

        return Convertion(
            sentId = data.id,
            sentAmount = data.amount,
            receivedId = id.toInt(),
            receivedAmount = quote.price,
            lastUpdated = data.lastUpdated,
        )
    }
}
