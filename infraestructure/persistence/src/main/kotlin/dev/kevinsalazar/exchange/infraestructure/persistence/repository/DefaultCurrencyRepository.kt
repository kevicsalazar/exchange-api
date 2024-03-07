package dev.kevinsalazar.exchange.infraestructure.persistence.repository

import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.ports.driven.CurrencyRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.CurrencyTable
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.batchUpsert
import org.jetbrains.exposed.sql.selectAll

class DefaultCurrencyRepository : CurrencyRepository {
    override suspend fun save(currencies: Set<Currency>) {
        return dbQuery {
            CurrencyTable.batchUpsert(currencies) {
                this[CurrencyTable.id] = it.id
                this[CurrencyTable.rank] = it.rank
                this[CurrencyTable.name] = it.name
                this[CurrencyTable.sign] = it.sign
                this[CurrencyTable.symbol] = it.symbol
            }
        }
    }

    override suspend fun findById(id: Int): Currency? {
        return dbQuery {
            CurrencyTable.selectAll()
                .where { CurrencyTable.id eq id }
                .map(::rowToCurrency)
                .singleOrNull()
        }
    }

    private fun rowToCurrency(row: ResultRow): Currency {
        return Currency(
            id = row[CurrencyTable.id],
            rank = row[CurrencyTable.rank],
            name = row[CurrencyTable.name],
            symbol = row[CurrencyTable.symbol],
            sign = row[CurrencyTable.sign],
        )
    }
}