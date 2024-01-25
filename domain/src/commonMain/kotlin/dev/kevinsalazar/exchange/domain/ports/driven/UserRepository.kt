package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.params.LoginParams
import dev.kevinsalazar.exchange.domain.params.RegisterParams

interface UserRepository {
    suspend fun register(params: RegisterParams): User?
    suspend fun login(params: LoginParams): User?
    suspend fun findByEmail(email: String): User?
}