package dev.kevinsalazar.exchange.infraestructure.persistence.tables

import org.jetbrains.exposed.sql.Table

object CurrencyTable : Table(name = "currencies") {
    val id = integer("id")
    val rank = integer("rank").nullable()
    val name = varchar("name", 32)
    val symbol = varchar("symbol", 8)
    val sign = varchar("sign", 8).nullable()

    override val primaryKey = PrimaryKey(id)
}