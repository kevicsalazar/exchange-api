package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.payload.request.SwapRequest

interface SwapUseCase {
    suspend fun execute(userId: String, params: SwapRequest): Result<Transaction>
}