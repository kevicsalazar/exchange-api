package dev.kevinsalazar.exchange.domain.usecases

import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.errors.EmailAlreadyRegisterdException
import dev.kevinsalazar.exchange.domain.params.RegisterParams
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase

internal class DefaultRegisterUseCase(
    private val userRepository: UserRepository
) : RegisterUseCase {
    override suspend fun execute(params: RegisterParams): Result<User> {
        try {

            if (userRepository.findByEmail(params.email) != null) {
                throw EmailAlreadyRegisterdException()
            }

            val user = userRepository.register(params)
            requireNotNull(user)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}