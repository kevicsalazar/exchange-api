package dev.kevinsalazar.exchange.domain.security


expect fun generateHash(password: String, salt: String): String
expect fun generateRandomSalt(): String