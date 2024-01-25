package dev.kevinsalazar.exchange.adapters.persist.tables

import dev.kevinsalazar.exchange.domain.enums.Status
import org.jetbrains.exposed.sql.Table

object ExchangesTable : Table(name = "exchanges") {
    val id = varchar("id", length = 36)
    val userId = varchar("user_id", length = 36)
    val status = enumeration<Status>("status")
    val sentCurrencyCode = varchar("sent_currency_code", length = 64)
    val sentAmount = varchar("sent_amount", length = 64)
    val receivedCurrencyCode = varchar("received_currency_code", length = 64)
    val receivedAmount = varchar("received_amount", length = 64)

    override val primaryKey = PrimaryKey(id)
}