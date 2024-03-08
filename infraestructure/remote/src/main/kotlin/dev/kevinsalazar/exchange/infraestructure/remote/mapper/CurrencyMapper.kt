package dev.kevinsalazar.exchange.infraestructure.remote.mapper

import dev.kevinsalazar.exchange.domain.values.Convertion
import dev.kevinsalazar.exchange.infraestructure.remote.dto.CryptoConvertion

object CurrencyMapper {

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
