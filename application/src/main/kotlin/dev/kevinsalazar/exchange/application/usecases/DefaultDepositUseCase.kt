package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.application.utils.generateUUID
import dev.kevinsalazar.exchange.domain.entities.Balance
import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.payload.request.DepositRequest
import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driving.DepositUseCase
import dev.kevinsalazar.exchange.domain.utils.getTimeStamp

class DefaultDepositUseCase(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: TransactionRepository
) : DepositUseCase {

    override suspend fun execute(userId: String, request: DepositRequest): Result<Transaction> {
        try {

            val transaction = Transaction(
                id = generateUUID(),
                userId = userId,
                receivedCurrencyCode = request.currencyCode,
                receivedAmount = request.amount,
                created = getTimeStamp()
            )

            val recipientBalance = balanceRepository.findBalance(userId, request.currencyCode)

            val recipientNewBalance = recipientBalance?.let {
                it.copy(
                    amount = it.amount + request.amount
                )
            } ?: Balance(
                id = generateUUID(),
                userId = userId,
                amount = request.amount,
                currencyCode = request.currencyCode
            )

            balanceRepository.updateBalance(recipientNewBalance)

            val savedTransaction = transactionRepository.save(transaction)

            requireNotNull(savedTransaction) { "Unable to save transaction" }

            return Result.success(transaction)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}