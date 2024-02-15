package dev.kevinsalazar.exchange.adapters.remote.utils

import kotlinx.serialization.json.Json

val json = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}