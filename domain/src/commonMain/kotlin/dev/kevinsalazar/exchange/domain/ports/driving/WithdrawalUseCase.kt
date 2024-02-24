package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.payload.request.WithdrawalRequest

interface WithdrawalUseCase {
    suspend fun execute(userId: String, request: WithdrawalRequest): Result<Transaction>
}