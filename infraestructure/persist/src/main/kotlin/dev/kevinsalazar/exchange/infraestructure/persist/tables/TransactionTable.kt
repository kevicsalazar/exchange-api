package dev.kevinsalazar.exchange.infraestructure.persist.tables

import dev.kevinsalazar.exchange.domain.enums.Status
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object TransactionTable : Table(name = "transactions") {
    val id = varchar("id", length = 36)
    val userId = reference("film_id", UsersTable.id)
    val status = enumeration<Status>("status")
    val sentCurrencyId = integer("sent_currency_id")
    val sentAmount = float("sent_amount")
    val receivedCurrencyId = integer("received_currency_id")
    val receivedAmount = float("received_amount")
    val created = timestamp("timestamp")

    override val primaryKey = PrimaryKey(id)
}