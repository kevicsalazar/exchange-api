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
                it[sentCurrencyCode] = transaction.sentCurrencyCode
                it[sentAmount] = transaction.sentAmount
                it[receivedCurrencyCode] = transaction.receivedCurrencyCode
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

    override suspend fun findById(id: String): Transaction? {
        return dbQuery {
            TransactionTable.selectAll()
                .where { TransactionTable.id eq id }
                .map(::rowToTransaction)
                .singleOrNull()
        }
    }

    private fun rowToTransaction(row: ResultRow): Transaction {
        return Transaction(
            id = row[TransactionTable.id],
            userId = row[TransactionTable.userId],
            status = row[TransactionTable.status],
            sentCurrencyCode = row[TransactionTable.sentCurrencyCode],
            sentAmount = row[TransactionTable.sentAmount],
            receivedCurrencyCode = row[TransactionTable.receivedCurrencyCode],
            receivedAmount = row[TransactionTable.receivedAmount],
            created = instantToString(row[TransactionTable.created])
        )
    }
}