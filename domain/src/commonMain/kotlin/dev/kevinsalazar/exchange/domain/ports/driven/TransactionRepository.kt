package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.enums.Status
import dev.kevinsalazar.exchange.domain.params.SwapParams

interface TransactionRepository {
    suspend fun save(userId: String, status: Status, params: SwapParams): Transaction?
}