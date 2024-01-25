package dev.kevinsalazar.exchange.adapters.persist.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.kevinsalazar.exchange.adapters.persist.tables.ExchangesTable
import dev.kevinsalazar.exchange.adapters.persist.tables.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun initDB() {
        val config = HikariConfig("/hikari.properties")
        val ds = HikariDataSource(config)
        val db = Database.connect(ds)
        createTables(db)
    }

    private fun createTables(db: Database) {
        transaction(db) {
            SchemaUtils.create(UsersTable)
            SchemaUtils.create(ExchangesTable)
        }
    }
}
