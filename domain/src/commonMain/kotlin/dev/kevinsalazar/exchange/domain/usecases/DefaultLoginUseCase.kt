package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.params.LoginParams
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.LoginUseCase

internal class DefaultLoginUseCase(
    private val userRepository: UserRepository
) : LoginUseCase {
    override suspend fun execute(params: LoginParams): Result<User> {
        try {
            val user = userRepository.login(params)
            requireNotNull(user)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}