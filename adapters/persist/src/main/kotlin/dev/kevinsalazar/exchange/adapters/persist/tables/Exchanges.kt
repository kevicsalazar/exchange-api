package dev.kevinsalazar.exchange.adapters.persist.tables

import dev.kevinsalazar.exchange.domain.enums.Status
import org.jetbrains.exposed.sql.Table

object Exchanges : Table() {
    val id = integer("id").autoIncrement()
    val userId = varchar("user_id", length = 50)
    val amount = varchar("email", length = 50)
    val status = enumeration<Status>("status")
    val sentCurrencyCode = varchar("sent_currency_code", length = 3)
    val sentAmount = varchar("sent_amount", length = 3)
    val receivedCurrencyCode = varchar("received_currency_code", length = 3)
    val receivedAmount = varchar("received_amount", length = 3)

    override val primaryKey = PrimaryKey(id)
}