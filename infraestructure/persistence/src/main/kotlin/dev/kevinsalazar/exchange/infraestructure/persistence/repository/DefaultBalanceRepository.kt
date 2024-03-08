package dev.kevinsalazar.exchange.infraestructure.persistence.repository

import dev.kevinsalazar.exchange.domain.entities.Balance
import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.BalanceTable
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.upsert

internal class DefaultBalanceRepository : BalanceRepository {

    override suspend fun findBalance(userId: String, currencyCode: String): Balance? {
        return dbQuery {
            BalanceTable.selectAll()
                .where { BalanceTable.userId eq userId }
                .andWhere { BalanceTable.currencyCode eq currencyCode }
                .map(::rowToBalance)
                .singleOrNull()
        }
    }

    override suspend fun findAll(userId: String): List<Balance> {
        return dbQuery {
            BalanceTable.selectAll()
                .where { BalanceTable.userId eq userId }
                .map(::rowToBalance)
        }
    }

    override suspend fun updateBalance(balance: Balance) {
        return dbQuery {
            BalanceTable.upsert {
                it[id] = balance.id
                it[userId] = balance.userId
                it[currencyCode] = balance.currencyCode
                it[amount] = balance.amount
            }
        }
    }

    private fun rowToBalance(row: ResultRow): Balance {
        return Balance(
            id = row[BalanceTable.id],
            userId = row[BalanceTable.userId],
            amount = row[BalanceTable.amount],
            currencyCode = row[BalanceTable.currencyCode]
        )
    }

}