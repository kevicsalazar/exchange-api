package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.errors.EmailAlreadyRegisterdException
import dev.kevinsalazar.exchange.domain.params.RegisterParams
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase
import dev.kevinsalazar.exchange.domain.utils.generateHash

internal class DefaultRegisterUseCase(
    private val userRepository: UserRepository,
    private val securityConfig: SecurityConfig
) : RegisterUseCase {
    override suspend fun execute(params: RegisterParams): Result<User> {
        try {

            if (userRepository.findByEmail(params.email) != null) {
                throw EmailAlreadyRegisterdException()
            }

            val password = generateHash(params.password, params.salt)
            val user = userRepository.register(params.copy(password = password))
            requireNotNull(user)

            user.authToken = securityConfig.createToken(user.id)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}