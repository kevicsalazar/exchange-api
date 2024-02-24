package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.payload.request.LoginRequest
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.LoginUseCase
import dev.kevinsalazar.exchange.application.utils.generateHash

internal class DefaultLoginUseCase(
    private val userRepository: UserRepository,
    private val securityConfig: SecurityConfig
) : LoginUseCase {
    override suspend fun execute(request: LoginRequest): Result<User> {
        try {
            val salt = userRepository.findSaltByEmail(request.email)
            requireNotNull(salt)

            val password = generateHash(request.password, salt)
            val user = userRepository.login(request.copy(password = password))
            requireNotNull(user)

            user.authToken = securityConfig.createToken(user.id)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}