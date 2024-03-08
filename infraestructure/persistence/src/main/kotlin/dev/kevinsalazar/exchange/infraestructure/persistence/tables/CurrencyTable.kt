package dev.kevinsalazar.exchange.infraestructure.persistence.tables

import org.jetbrains.exposed.sql.Table

object CurrencyTable : Table(name = "currencies") {
    val code = varchar("code", 8)
    val name = varchar("name", 32)
    val locale = varchar("symbol", 8)

    override val primaryKey = PrimaryKey(code)
}