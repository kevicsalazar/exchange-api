package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.values.Convertion

interface ConvertUseCase {
    suspend fun execute(amount: Double, send: String, receive: String): Result<Convertion>
}