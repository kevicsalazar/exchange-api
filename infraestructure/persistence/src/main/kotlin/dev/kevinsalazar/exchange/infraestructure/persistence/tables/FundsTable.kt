package dev.kevinsalazar.exchange.infraestructure.persistence.tables

import org.jetbrains.exposed.sql.Table

object FundsTable : Table(name = "funds") {
    val id = varchar("id", length = 36)
    val userId = reference("film_id", UsersTable.id)
    val amount = float("amount")
    val currencyId = integer("sent_currency_id")

    override val primaryKey = PrimaryKey(id)
}