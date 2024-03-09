package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.application.utils.generateRandomSalt
import dev.kevinsalazar.exchange.application.utils.generateUUID
import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.errors.EmailAlreadyRegisterdException
import dev.kevinsalazar.exchange.domain.payload.request.RegisterRequest
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import dev.kevinsalazar.exchange.domain.ports.driven.events.EventBus
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.mockative.*

class RegisterUseCaseTest : StringSpec() {

    @Mock
    val securityConfig = mock(classOf<SecurityConfig>())

    @Mock
    val userRepository = mock(classOf<UserRepository>())

    @Mock
    val eventBus = mock(classOf<EventBus>())

    private val useCase by lazy { DefaultRegisterUseCase(userRepository, securityConfig, eventBus) }

    init {
        "GIVEN RegisterParams WHEN execute THEN return User" {

            coEvery { userRepository.findByEmail(any()) }.invokes { _ -> null }
            coEvery { userRepository.register(any()) }.invokes { _ -> User(id = generateUUID(), name = "Kevin", email = "kevin@google.com") }
            every { securityConfig.createToken(any()) }.invokes { _ -> "token" }

            val params = RegisterRequest(name = "Kevin", email = "kevin@google.com", password = "pwd", salt = generateRandomSalt())

            val result = useCase.execute(params)

            result.isSuccess shouldBe true
            requireNotNull(result.getOrNull()).apply {
                name shouldBe "Kevin_"
                email shouldBe "kevin@google.com"
                authToken shouldBe "token"
            }
        }
        "GIVEN RegisterParams WHEN execute and email already exist THEN return User" {

            coEvery { userRepository.findByEmail(any()) }.invokes { _ -> User(id = generateUUID(), name = "Kevin", email = "kevin@google.com") }
            coEvery { userRepository.register(any()) }.invokes { _ -> User(id = generateUUID(), name = "Kevin", email = "kevin@google.com") }
            every { securityConfig.createToken(any()) }.invokes { _ -> "token" }

            val params = RegisterRequest(name = "Kevin", email = "kevin@google.com", password = "pwd", salt = generateRandomSalt())

            val result = useCase.execute(params)

            result.isFailure shouldBe true
            requireNotNull(result.exceptionOrNull()) shouldNotBeSameInstanceAs EmailAlreadyRegisterdException::class.java
        }
    }

}