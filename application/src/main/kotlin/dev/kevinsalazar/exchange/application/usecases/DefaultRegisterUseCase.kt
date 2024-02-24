package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.errors.EmailAlreadyRegisterdException
import dev.kevinsalazar.exchange.domain.payload.request.RegisterRequest
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase
import dev.kevinsalazar.exchange.application.utils.generateHash
import dev.kevinsalazar.exchange.application.utils.generateUUID

internal class DefaultRegisterUseCase(
    private val userRepository: UserRepository,
    private val securityConfig: SecurityConfig
) : RegisterUseCase {
    override suspend fun execute(params: RegisterRequest): Result<User> {
        try {

            if (userRepository.findByEmail(params.email) != null) {
                throw EmailAlreadyRegisterdException()
            }

            val password = generateHash(params.password, params.salt)
            val user = userRepository.register(params.copy(
                id = generateUUID(),
                password = password
            ))
            requireNotNull(user)

            user.authToken = securityConfig.createToken(user.id)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}