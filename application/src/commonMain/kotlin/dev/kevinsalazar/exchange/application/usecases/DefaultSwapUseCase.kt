package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.enums.Status
import dev.kevinsalazar.exchange.domain.errors.InsufficientFundsException
import dev.kevinsalazar.exchange.domain.payload.request.SwapRequest
import dev.kevinsalazar.exchange.domain.ports.driven.FundsRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driving.SwapUseCase
import dev.kevinsalazar.exchange.application.utils.generateUUID
import dev.kevinsalazar.exchange.domain.utils.getTimeStamp

internal class DefaultSwapUseCase(
    private val fundsRepository: FundsRepository,
    private val transactionRepository: TransactionRepository
) : SwapUseCase {

    override suspend fun execute(userId: String, params: SwapRequest): Result<Transaction> {

        val transaction = Transaction(
            id = generateUUID(),
            userId = userId,
            status = Status.Success,
            sentCurrencyId = params.from.currencyId,
            sentAmount = params.from.amount,
            receivedCurrencyId = params.to.currencyId,
            receivedAmount = params.to.amount,
            created = getTimeStamp()
        )

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

                val savedTransaction = transactionRepository.save(transaction)

                requireNotNull(savedTransaction) { "Unable to save transaction" }

                return Result.success(transaction)
            } else {
                transactionRepository.save(
                    transaction.copy(
                        status = Status.Error
                    )
                )
                throw InsufficientFundsException()
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}