package dev.kevinsalazar.exchange.adapters.persist.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    fun initDB(): Database {
        val config = HikariConfig("/hikari.properties")
        val ds = HikariDataSource(config)
        return Database.connect(ds)
    }
}
