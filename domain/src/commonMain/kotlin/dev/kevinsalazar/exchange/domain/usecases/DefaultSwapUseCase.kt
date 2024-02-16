package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.enums.Status
import dev.kevinsalazar.exchange.domain.errors.InsufficientFundsException
import dev.kevinsalazar.exchange.domain.params.SwapParams
import dev.kevinsalazar.exchange.domain.ports.driven.FundsRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driving.SwapUseCase

internal class DefaultSwapUseCase(
    private val fundsRepository: FundsRepository,
    private val transactionRepository: TransactionRepository
) : SwapUseCase {

    override suspend fun execute(userId: String, params: SwapParams): Result<Transaction> {
        try {
            val fromFunds = fundsRepository.findfunds(userId, params.from.currencyId)
            if (fromFunds >= params.from.amount) {

                fundsRepository.updateFunds(
                    userId = userId,
                    currencyId = params.from.currencyId,
                    amount = fromFunds - params.from.amount
                )

                val toFunds = fundsRepository.findfunds(userId, params.to.currencyId)

                fundsRepository.updateFunds(
                    userId = userId,
                    currencyId = params.to.currencyId,
                    amount = toFunds + params.to.amount
                )

                val transaction = transactionRepository.save(userId, Status.Success, params)

                requireNotNull(transaction) { "Unable to save transaction" }

                return Result.success(transaction)
            } else {
                transactionRepository.save(userId, Status.Error, params)
                throw InsufficientFundsException()
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}