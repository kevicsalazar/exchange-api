package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Balance
import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.domain.ports.driving.ListBalancesUseCase

class DefaultListBalancesUseCase(
    private val balanceRepository: BalanceRepository
): ListBalancesUseCase {

    override suspend fun execute(userId: String): Result<List<Balance>> {
        try {
            val data = balanceRepository.findAll(userId)
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}