package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.errors.EmailAlreadyRegisterdException
import dev.kevinsalazar.exchange.domain.params.RegisterParams
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase
import dev.kevinsalazar.exchange.domain.security.generateHash

internal class DefaultRegisterUseCase(
    private val userRepository: UserRepository
) : RegisterUseCase {
    override suspend fun execute(params: RegisterParams): Result<User> {
        try {

            if (userRepository.findByEmail(params.email) != null) {
                throw EmailAlreadyRegisterdException()
            }

            val passwordHash = generateHash(params.password, params.salt)

            val user = userRepository.register(
                params.copy(password = passwordHash)
            )
            requireNotNull(user)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}