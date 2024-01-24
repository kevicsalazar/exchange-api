package dev.kevinsalazar.exchange.adapters.persist.repository

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository

internal class DefaultUserRepository : UserRepository{
    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun find(email: String, password: String): User {
        TODO("Not yet implemented")
    }
}