package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Transaction

interface ListTransactionsUseCase {
    suspend fun execute(userId: String): Result<List<Transaction>>
}