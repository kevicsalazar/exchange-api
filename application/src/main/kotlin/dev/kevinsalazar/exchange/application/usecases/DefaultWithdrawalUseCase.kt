package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.application.utils.generateUUID
import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.enums.Status
import dev.kevinsalazar.exchange.domain.errors.InsufficientFundsException
import dev.kevinsalazar.exchange.domain.payload.request.WithdrawalRequest
import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driving.WithdrawalUseCase
import dev.kevinsalazar.exchange.domain.utils.getTimeStamp

class DefaultWithdrawalUseCase(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: TransactionRepository
) : WithdrawalUseCase {

    override suspend fun execute(userId: String, request: WithdrawalRequest): Result<Transaction> {

        val transaction = Transaction(
            id = generateUUID(),
            userId = userId,
            status = Status.Success,
            sentCurrencyId = request.currencyId,
            sentAmount = request.amount,
            created = getTimeStamp()
        )

        try {

            val recipientBalance = balanceRepository.findBalance(userId, request.currencyId)

            if (recipientBalance != null && recipientBalance.amount >= request.amount) {

                val recipientNewBalance = recipientBalance.copy(
                    amount = recipientBalance.amount - request.amount
                )

                balanceRepository.updateBalance(recipientNewBalance)

                val savedTransaction = transactionRepository.save(transaction)

                requireNotNull(savedTransaction) { "Unable to save transaction" }

                return Result.success(transaction)
            } else {
                throw InsufficientFundsException()
            }
        } catch (e: Exception) {
            transactionRepository.save(
                transaction.copy(
                    status = Status.Error
                )
            )
            return Result.failure(e)
        }
    }

}