package dev.kevinsalazar.exchange.adapters.persist.repository

import dev.kevinsalazar.exchange.adapters.persist.tables.UsersTable
import dev.kevinsalazar.exchange.adapters.persist.utils.dbQuery
import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.params.LoginParams
import dev.kevinsalazar.exchange.domain.params.RegisterParams
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

internal class DefaultUserRepository : UserRepository {

    override suspend fun register(params: RegisterParams): User? {
        return dbQuery {
            UsersTable.insert {
                it[name] = params.name
                it[email] = params.email
                it[password] = params.password
            }.resultedValues
                ?.firstOrNull()
                ?.let(::rowToUser)
        }
    }

    override suspend fun login(params: LoginParams): User? {
        return dbQuery {
            UsersTable.selectAll()
                .where { UsersTable.email eq params.email }
                .andWhere { UsersTable.password eq params.password }
                .map { User(it[UsersTable.id], it[UsersTable.name], it[UsersTable.email]) }
                .singleOrNull()
        }
    }

    override suspend fun findByEmail(email: String): User? {
        return dbQuery {
            UsersTable.selectAll()
                .where { UsersTable.email eq email }
                .map { User(it[UsersTable.id], it[UsersTable.name], it[UsersTable.email]) }
                .singleOrNull()
        }
    }

    private fun rowToUser(row: ResultRow): User {
        return User(
            id = row[UsersTable.id],
            name = row[UsersTable.name],
            email = row[UsersTable.email],
        )
    }

}