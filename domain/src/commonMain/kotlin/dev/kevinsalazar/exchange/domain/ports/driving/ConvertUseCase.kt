package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Convertion

interface ConvertUseCase {
    suspend fun execute(amount: Float, from: Int, to: Int): Result<Convertion>
}