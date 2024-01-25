package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.params.LoginParams

interface LoginUseCase {
    suspend fun execute(params: LoginParams): Result<User>
}