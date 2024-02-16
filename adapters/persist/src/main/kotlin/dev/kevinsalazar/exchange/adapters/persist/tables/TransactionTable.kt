package dev.kevinsalazar.exchange.adapters.persist.tables

import dev.kevinsalazar.exchange.domain.enums.Status
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object TransactionTable : Table(name = "exchanges") {
    val id = varchar("id", length = 36)
    val userId = varchar("user_id", length = 36)
    val status = enumeration<Status>("status")
    val sentCurrencyId = integer("sent_currency_code")
    val sentAmount = float("sent_amount")
    val receivedCurrencyId = integer("received_currency_code")
    val receivedAmount = float("received_amount")
    val created = timestamp("timestamp")

    override val primaryKey = PrimaryKey(id)
}