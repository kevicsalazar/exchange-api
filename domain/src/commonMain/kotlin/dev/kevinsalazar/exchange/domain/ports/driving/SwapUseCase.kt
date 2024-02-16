package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.params.SwapParams

interface SwapUseCase {
    suspend fun execute(userId: String, params: SwapParams): Result<Transaction>
}