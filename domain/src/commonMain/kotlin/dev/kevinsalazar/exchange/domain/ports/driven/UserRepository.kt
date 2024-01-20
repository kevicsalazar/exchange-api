package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.User

interface UserRepository {
    fun save(user: User)
    fun find(email: String, password: String): User
}