package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.params.LoginParams
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.LoginUseCase
import dev.kevinsalazar.exchange.domain.utils.generateHash

internal class DefaultLoginUseCase(
    private val userRepository: UserRepository,
    private val securityConfig: SecurityConfig
) : LoginUseCase {
    override suspend fun execute(params: LoginParams): Result<User> {
        try {
            val salt = userRepository.findSaltByEmail(params.email)
            requireNotNull(salt)

            val password = generateHash(params.password, salt)
            val user = userRepository.login(params.copy(password = password))
            requireNotNull(user)

            user.authToken = securityConfig.createToken(user.id)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}