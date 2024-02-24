package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.payload.request.LoginRequest

interface LoginUseCase {
    suspend fun execute(request: LoginRequest): Result<User>
}