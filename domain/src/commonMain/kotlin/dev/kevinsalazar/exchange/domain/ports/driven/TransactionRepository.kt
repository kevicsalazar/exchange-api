package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.Transaction

interface TransactionRepository {
    suspend fun save(transaction: Transaction): Transaction?
}