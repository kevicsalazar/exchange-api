package dev.kevinsalazar.exchange.domain.ports.driving

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.params.RegisterParams

interface RegisterUseCase {
    suspend fun execute(params: RegisterParams): Result<User>
}