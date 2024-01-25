package dev.kevinsalazar.exchange.domain.ports.driven

interface SecurityConfig {
    fun createToken(claim: String): String
}