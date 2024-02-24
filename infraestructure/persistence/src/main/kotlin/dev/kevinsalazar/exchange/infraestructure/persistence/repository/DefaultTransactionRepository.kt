package dev.kevinsalazar.exchange.infraestructure.persistence.repository

import dev.kevinsalazar.exchange.domain.entities.Transaction
import dev.kevinsalazar.exchange.domain.ports.driven.TransactionRepository
import dev.kevinsalazar.exchange.domain.utils.instantToString
import dev.kevinsalazar.exchange.domain.utils.stringToInstant
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.TransactionTable
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

internal class DefaultTransactionRepository : TransactionRepository {

    override suspend fun save(transaction: Transaction): Transaction? {
        return dbQuery {
            TransactionTable.insert {
                it[id] = transaction.id
                it[userId] = transaction.userId
                it[status] = transaction.status
                it[sentCurrencyId] = transaction.sentCurrencyId
                it[sentAmount] = transaction.sentAmount
                it[receivedCurrencyId] = transaction.receivedCurrencyId
                it[receivedAmount] = transaction.receivedAmount
                it[created] = stringToInstant(transaction.created)
            }.resultedValues
                ?.firstOrNull()
                ?.let(::rowToTransaction)
        }
    }

    override suspend fun findAll(userId: String): List<Transaction> {
        return dbQuery {
            TransactionTable.selectAll()
                .where { TransactionTable.userId eq userId }
                .map(::rowToTransaction)
        }
    }

    private fun rowToTransaction(row: ResultRow): Transaction {
        return Transaction(
            id = row[TransactionTable.id],
            userId = row[TransactionTable.userId],
            status = row[TransactionTable.status],
            sentCurrencyId = row[TransactionTable.sentCurrencyId],
            sentAmount = row[TransactionTable.sentAmount],
            receivedCurrencyId = row[TransactionTable.receivedCurrencyId],
            receivedAmount = row[TransactionTable.receivedAmount],
            created = instantToString(row[TransactionTable.created])
        )
    }
}