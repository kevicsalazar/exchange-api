package dev.kevinsalazar.exchange.adapters.persist.repository

import dev.kevinsalazar.exchange.adapters.persist.tables.TransactionTable
import dev.kevinsalazar.exchange.adapters.persist.utils.dbQuery
import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.enums.Status
import dev.kevinsalazar.exchange.domain.params.SwapParams
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.utils.generateUUID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import java.time.Instant

internal class DefaultTransactionRepository : TransactionRepository {

    override suspend fun save(userId: String, status: Status, params: SwapParams): Transaction? {
        return dbQuery {
            TransactionTable.insert {
                it[id] = generateUUID()
                it[TransactionTable.userId] = userId
                it[TransactionTable.status] = status
                it[sentCurrencyId] = params.from.currencyId
                it[sentAmount] = params.from.amount
                it[receivedCurrencyId] = params.to.currencyId
                it[receivedAmount] = params.to.amount
                it[created] = Instant.now()
            }.resultedValues
                ?.firstOrNull()
                ?.let(::rowToTransaction)
        }
    }

    private fun rowToTransaction(row: ResultRow): Transaction {
        return Transaction(
            id = row[TransactionTable.id],
            status = row[TransactionTable.status],
            sentCurrencyId = row[TransactionTable.sentCurrencyId],
            sentAmount = row[TransactionTable.sentAmount],
            receivedCurrencyId = row[TransactionTable.receivedCurrencyId],
            receivedAmount = row[TransactionTable.receivedAmount],
            created = row[TransactionTable.created].toString(),

            )
    }
}