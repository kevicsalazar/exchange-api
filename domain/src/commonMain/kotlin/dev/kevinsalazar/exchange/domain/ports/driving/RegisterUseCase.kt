package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.payload.request.RegisterRequest

interface RegisterUseCase {
    suspend fun execute(params: RegisterRequest): Result<User>
}