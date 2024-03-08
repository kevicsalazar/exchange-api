package dev.kevinsalazar.exchange.infraestructure.persistence.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.io.InputStreamReader

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }

inline fun <reified T> Any.loadFromFile(filePath: String): T? {
    val json = Json { ignoreUnknownKeys = true }
    val inputStream = javaClass.classLoader.getResourceAsStream(filePath)
    return InputStreamReader(requireNotNull(inputStream)).use {
        json.decodeFromString<T>(it.readText())
    }
}
