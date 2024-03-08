package dev.kevinsalazar.exchange.infraestructure.persistence.repository

import dev.kevinsalazar.exchange.domain.entities.Currency
import dev.kevinsalazar.exchange.domain.ports.driven.CurrencyRepository
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.CurrencyTable
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.batchUpsert
import org.jetbrains.exposed.sql.selectAll

class DefaultCurrencyRepository : CurrencyRepository {

    override suspend fun findAll(): List<Currency> {
        return dbQuery {
            CurrencyTable.selectAll()
                .map(::rowToCurrency)
        }
    }

    override suspend fun findByCode(code: String): Currency? {
        return dbQuery {
            CurrencyTable.selectAll()
                .where { CurrencyTable.code eq code }
                .map(::rowToCurrency)
                .singleOrNull()
        }
    }

    private fun rowToCurrency(row: ResultRow): Currency {
        return Currency(
            code = row[CurrencyTable.code],
            name = row[CurrencyTable.name],
            locale = row[CurrencyTable.locale]
        )
    }

    override suspend fun save(currencies: List<Currency>) {
        return dbQuery {
            CurrencyTable.batchUpsert(currencies) {
                this[CurrencyTable.code] = it.code
                this[CurrencyTable.name] = it.name
                this[CurrencyTable.locale] = it.locale
            }
        }
    }
}