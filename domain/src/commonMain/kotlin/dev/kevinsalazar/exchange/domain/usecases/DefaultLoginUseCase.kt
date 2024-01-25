package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.params.LoginParams
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.LoginUseCase
import dev.kevinsalazar.exchange.domain.security.generateHash

internal class DefaultLoginUseCase(
    private val userRepository: UserRepository
) : LoginUseCase {
    override suspend fun execute(params: LoginParams): Result<User> {
        try {
            val salt = userRepository.findSaltByEmail(params.email)
            val password = generateHash(params.password, requireNotNull(salt))
            val user = userRepository.login(params.copy(password = password))
            return Result.success(requireNotNull(user))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}