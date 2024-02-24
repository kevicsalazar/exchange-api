package dev.kevinsalazar.exchange.infraestructure.persistence.repository

import dev.kevinsalazar.exchange.infraestructure.persistence.tables.UsersTable
import dev.kevinsalazar.exchange.infraestructure.persistence.utils.dbQuery
import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.payload.request.LoginRequest
import dev.kevinsalazar.exchange.domain.payload.request.RegisterRequest
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

internal class DefaultUserRepository : UserRepository {

    override suspend fun register(params: RegisterRequest): User? {
        return dbQuery {
            UsersTable.insert {
                it[id] = requireNotNull(params.id)
                it[name] = params.name
                it[email] = params.email
                it[password] = params.password
                it[salt] = params.salt
            }.resultedValues
                ?.firstOrNull()
                ?.let(::rowToUser)
        }
    }

    override suspend fun login(params: LoginRequest): User? {
        return dbQuery {
            UsersTable.selectAll()
                .where { UsersTable.email eq params.email }
                .andWhere { UsersTable.password eq params.password }
                .map { User(it[UsersTable.id], it[UsersTable.name], it[UsersTable.email]) }
                .singleOrNull()
        }
    }

    override suspend fun findSaltByEmail(email: String): String? {
        return dbQuery {
            UsersTable.selectAll()
                .where { UsersTable.email eq email }
                .map { it[UsersTable.salt] }
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