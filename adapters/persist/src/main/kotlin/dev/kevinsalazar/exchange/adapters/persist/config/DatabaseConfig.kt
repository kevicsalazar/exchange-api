package dev.kevinsalazar.exchange.adapters.persist.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.kevinsalazar.exchange.adapters.persist.tables.TransactionTable
import dev.kevinsalazar.exchange.adapters.persist.tables.UsersTable
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseConfig {
    fun initialize(properties: ConfigProperties.Database) {
        val config = HikariConfig().apply {
            username = properties.username
            password = properties.password
            jdbcUrl = properties.url
        }
        val ds = HikariDataSource(config)
        val db = Database.connect(ds)
        createTables(db)
    }

    private fun createTables(db: Database) {
        transaction(db) {
            SchemaUtils.create(UsersTable)
            SchemaUtils.create(TransactionTable)
        }
    }
}
