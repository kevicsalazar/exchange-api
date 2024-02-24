package dev.kevinsalazar.exchange.infraestructure.persistence.tables

import org.jetbrains.exposed.sql.Table

object BalanceTable : Table(name = "balances") {
    val id = varchar("id", length = 64)
    val userId = reference("user_id", UsersTable.id)
    val amount = float("amount")
    val currencyId = integer("sent_currency_id")

    override val primaryKey = PrimaryKey(id)
}