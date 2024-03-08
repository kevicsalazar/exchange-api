package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.values.Convertion

interface ConvertUseCase {
    suspend fun execute(amount: Float, from: String, to: String): Result<Convertion>
}