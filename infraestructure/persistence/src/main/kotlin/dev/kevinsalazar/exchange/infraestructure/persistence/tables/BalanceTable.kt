package dev.kevinsalazar.exchange.infraestructure.persistence.tables

import org.jetbrains.exposed.sql.Table

object BalanceTable : Table(name = "balances") {
    val id = varchar("id", length = 36)
    val userId = reference("user_id", UsersTable.id)
    val amount = double("amount")
    val currencyCode = reference("currency_code", CurrencyTable.code)

    override val primaryKey = PrimaryKey(id)
}