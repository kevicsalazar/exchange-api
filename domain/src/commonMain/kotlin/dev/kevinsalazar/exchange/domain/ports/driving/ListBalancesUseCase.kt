package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.Balance

interface ListBalancesUseCase {
    suspend fun execute(userId: String): Result<List<Balance>>
}