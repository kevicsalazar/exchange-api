package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.application.utils.generateUUID
import dev.kevinsalazar.exchange.domain.entities.User
import dev.kevinsalazar.exchange.domain.payload.request.LoginRequest
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import dev.kevinsalazar.exchange.domain.ports.driven.UserRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockative.*

class LoginUseCaseTest : StringSpec() {

    @Mock
    val securityConfig = mock(classOf<SecurityConfig>())

    @Mock
    val userRepository = mock(classOf<UserRepository>())

    private val useCase by lazy { DefaultLoginUseCase(userRepository, securityConfig) }

    init {
        "GIVEN LoginParams WHEN execute THEN return User" {

            coEvery { userRepository.findSaltByEmail(any()) }.invokes { _ -> "197375a5f149f89e979f281877705645" }
            coEvery { userRepository.login(any()) }.invokes { _ -> User(id = generateUUID(), name = "Kevin", email = "kevin@google.com") }
            every { securityConfig.createToken(any()) }.invokes { _ -> "token" }

            val params = LoginRequest(email = "kevin@google.com", password = "pwd")

            val result = useCase.execute(params)

            result.isSuccess shouldBe true
            requireNotNull(result.getOrNull()).apply {
                name shouldBe "Kevin"
                email shouldBe "kevin@google.com"
                authToken shouldBe "token"
            }
        }
    }

}