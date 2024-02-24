package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.payload.request.DepositRequest

interface DepositUseCase {
    suspend fun execute(userId: String, request: DepositRequest): Result<Transaction>
}