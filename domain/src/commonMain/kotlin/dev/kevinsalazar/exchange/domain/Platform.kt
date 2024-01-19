package dev.kevinsalazar.exchange.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform