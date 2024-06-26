package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.application.utils.generateUUID
import dev.kevinsalazar.exchange.domain.entities.Balance
import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.errors.InsufficientFundsException
import dev.kevinsalazar.exchange.domain.events.SuccessfulSwapEvent
import dev.kevinsalazar.exchange.domain.payload.request.SwapRequest
import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import dev.kevinsalazar.exchange.domain.ports.driving.SwapUseCase
import dev.kevinsalazar.exchange.domain.utils.getTimeStamp

internal class DefaultSwapUseCase(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: TransactionRepository,
    private val eventBus: EventBus
) : SwapUseCase {

    override suspend fun execute(userId: String, request: SwapRequest): Result<Transaction> {
        try {

            val transaction = Transaction(
                id = generateUUID(),
                userId = userId,
                sentCurrencyCode = request.send.currencyCode,
                sentAmount = request.send.amount,
                receivedCurrencyCode = request.receive.currencyCode,
                receivedAmount = request.receive.amount,
                created = getTimeStamp()
            )

            val senderBalance = balanceRepository.findBalance(userId, request.send.currencyCode)

            if (senderBalance == null || senderBalance.amount < request.send.amount) {
                throw InsufficientFundsException()
            }

            val senderNewBalance = senderBalance.copy(
                amount = senderBalance.amount - request.send.amount
            )

            val recipientBalance = balanceRepository.findBalance(userId, request.receive.currencyCode)

            val recipientNewBalance = recipientBalance?.let {
                it.copy(
                    amount = it.amount + request.receive.amount
                )
            } ?: Balance(
                id = generateUUID(),
                userId = userId,
                amount = request.receive.amount,
                currencyCode = request.receive.currencyCode
            )

            balanceRepository.updateBalance(senderNewBalance)
            balanceRepository.updateBalance(recipientNewBalance)

            val savedTransaction = transactionRepository.save(transaction)

            requireNotNull(savedTransaction) { "Unable to save transaction" }

            publishEvent(savedTransaction)

            return Result.success(transaction)

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private suspend fun publishEvent(transaction: Transaction) {
        val event = SuccessfulSwapEvent(
            transactionId = transaction.id,
            userId = transaction.userId,
            sentCurrencyCode = transaction.sentCurrencyCode,
            sentAmount = transaction.sentAmount,
            receivedCurrencyCode = transaction.receivedCurrencyCode,
            receivedAmount = transaction.receivedAmount,
            created = transaction.created
        )
        eventBus.publish(event)
    }

}