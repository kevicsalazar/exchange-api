package dev.kevinsalazar.exchange.infraestructure.persistence.repository

import dev.kevinsalazar.exchange.domain.entities.Balance
import dev.kevinsalazar.exchange.domain.ports.driven.BalanceRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.BalanceTable
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.dbQuery
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.upsert

internal class DefaultBalanceRepository : BalanceRepository {

    override suspend fun findBalance(userId: String, currencyId: Int): Balance? {
        return dbQuery {
            BalanceTable.selectAll()
                .where { BalanceTable.userId eq userId }
                .andWhere { BalanceTable.currencyId eq currencyId }
                .map {
                    Balance(
                        id = it[BalanceTable.id],
                        userId = it[BalanceTable.userId],
                        amount = it[BalanceTable.amount],
                        currencyId = it[BalanceTable.currencyId]
                    )
                }
                .singleOrNull()
        }
    }

    override suspend fun updateBalance(balance: Balance) {
        return dbQuery {
            BalanceTable.upsert {
                it[id] = balance.id
                it[userId] = balance.userId
                it[currencyId] = balance.currencyId
                it[amount] = balance.amount
            }
        }
    }

}