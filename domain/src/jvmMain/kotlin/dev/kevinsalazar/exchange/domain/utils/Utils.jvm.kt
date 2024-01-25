package dev.kevinsalazar.exchange.domain.utils

import java.util.*

actual fun generateUUID(): String {
    return UUID.randomUUID().toString()
}