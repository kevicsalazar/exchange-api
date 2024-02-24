package dev.kevinsalazar.exchange.infraestructure.persistence.repository

import dev.kevinsalazar.exchange.domain.entities.Balance
import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.FundsTable
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

internal class DefaultBalanceRepository : BalanceRepository {

    override suspend fun findBalance(userId: String, currencyId: Int): Balance? {
        return dbQuery {
            FundsTable.selectAll()
                .where { FundsTable.userId eq userId }
                .map {
                    Balance(
                        id = it[FundsTable.id],
                        userId = it[FundsTable.userId],
                        amount = it[FundsTable.amount],
                        currencyId = it[FundsTable.currencyId]
                    )
                }
                .singleOrNull()
        }
    }

    override suspend fun updateBalance(balance: Balance) {
        return dbQuery {
            FundsTable.insert {
                it[id] = balance.id
                it[userId] = balance.userId
                it[currencyId] = balance.currencyId
                it[amount] = balance.amount
            }
        }
    }

}