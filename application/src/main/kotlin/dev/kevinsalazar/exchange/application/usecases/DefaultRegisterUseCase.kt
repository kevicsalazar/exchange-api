package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.application.utils.generateHash
import dev.kevinsalazar.exchange.application.utils.generateUUID
import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.errors.EmailAlreadyRegisterdException
import dev.kevinsalazar.exchange.domain.events.UserRegisteredEvent
import dev.kevinsalazar.exchange.domain.payload.request.RegisterRequest
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driving.RegisterUseCase
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus

internal class DefaultRegisterUseCase(
    private val userRepository: UserRepository,
    private val securityConfig: SecurityConfig,
    private val eventBus: EventBus
) : RegisterUseCase {
    override suspend fun execute(request: RegisterRequest): Result<User> {
        try {

            if (userRepository.findByEmail(request.email) != null) {
                throw EmailAlreadyRegisterdException()
            }

            val password = generateHash(request.password, request.salt)
            val user = userRepository.register(
                request.copy(
                    id = generateUUID(),
                    password = password
                )
            )
            requireNotNull(user)

            user.authToken = securityConfig.createToken(user.id)

            publishEvent(user.id, user.email)

            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private suspend fun publishEvent(userId: String, email: String) {
        val event = UserRegisteredEvent(
            userId = userId,
            email = email
        )

        eventBus.publish(event)
    }
}