package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.payload.request.LoginRequest
import dev.kevinsalazar.exchange.domain.payload.request.RegisterRequest

interface UserRepository {
    suspend fun register(params: RegisterRequest): User?
    suspend fun login(params: LoginRequest): User?
    suspend fun findSaltByEmail(email: String): String?
    suspend fun findByEmail(email: String): User?
}