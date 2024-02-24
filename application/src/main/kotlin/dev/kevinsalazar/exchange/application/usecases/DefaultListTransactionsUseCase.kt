package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ListTransactionsUseCase

class DefaultListTransactionsUseCase(
    private val transactionRepository: TransactionRepository
) : ListTransactionsUseCase {

    override suspend fun execute(userId: String): Result<List<Transaction>> {
        try {
            val data = transactionRepository.findAll(userId)
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}