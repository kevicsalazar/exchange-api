package dev.kevinsalazar.exchange.infraestructure.persistence.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.BalanceTable
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.CurrencyTable
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.TransactionTable
import dev.kevinsalazar.exchange.infraestructure.persistence.tables.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


class DatabaseConfig(
    private val properties: ConfigProperties.Database,
    private val initialDataLoader: InitialDataLoader
) {
    init {
        val config = HikariConfig().apply {
            username = properties.username
            password = properties.password
            jdbcUrl = properties.url
        }
        val ds = HikariDataSource(config)
        val db = Database.connect(ds)
        createTables(db)
        loadInitialData()
    }

    private fun createTables(db: Database) {
        transaction(db) {
            SchemaUtils.create(UsersTable)
            SchemaUtils.create(TransactionTable)
            SchemaUtils.create(BalanceTable)
            SchemaUtils.create(CurrencyTable)
        }
    }

    private fun loadInitialData() {
        initialDataLoader.loadInitialData()
    }

}
