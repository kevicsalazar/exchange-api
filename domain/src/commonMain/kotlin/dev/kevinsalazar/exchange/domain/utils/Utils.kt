package dev.kevinsalazar.exchange.domain.utils

expect fun generateUUID(): String
expect fun generateHash(password: String, salt: String): String
expect fun generateRandomSalt(): String