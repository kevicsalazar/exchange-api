package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.values.Convertion

interface ExchangeRepository {
    suspend fun convert(amount: Double, send: String, receive: String): List<Convertion>
}