package dev.kevinsalazar.exchange.infraestructure.persist.tables

import org.jetbrains.exposed.sql.Table

object UsersTable : Table(name = "users") {
    val id = varchar("id", 36)
    val name = varchar("name", length = 64)
    val email = varchar("email", length = 64)
    val password = varchar("password", length = 64)
    val salt = varchar("salt", length = 32)

    override val primaryKey = PrimaryKey(id)
}