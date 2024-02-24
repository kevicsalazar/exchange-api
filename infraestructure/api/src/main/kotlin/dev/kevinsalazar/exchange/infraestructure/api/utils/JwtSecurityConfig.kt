package dev.kevinsalazar.exchange.infraestructure.api.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import dev.kevinsalazar.exchange.domain.ports.driven.SecurityConfig
import java.time.Instant

internal class JwtSecurityConfig(
    properties: ConfigProperties.Jwt,
) : SecurityConfig {

    private val secret = properties.secret
    private val issuer = properties.issuer
    private val audience = properties.audience

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()

    override fun createToken(claim: String): String = JWT
        .create()
        .withIssuer(issuer)
        .withAudience(audience)
        .withClaim(CLAIM, claim)
        .withExpiresAt(Instant.now().plusSeconds(DURATION))
        .sign(algorithm)

    companion object {
        const val CLAIM = "claim"
        const val DURATION: Long = 1 * 60 * 5
    }

}