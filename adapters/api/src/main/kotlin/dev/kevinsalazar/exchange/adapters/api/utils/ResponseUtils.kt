package dev.kevinsalazar.exchange.adapters.api.utils

import dev.kevinsalazar.exchange.adapters.api.model.Error
import dev.kevinsalazar.exchange.domain.errors.UnauthorizedException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

private inline fun <reified T> Result<T>.toResponse(): Pair<HttpStatusCode, Any> {
    return if (isSuccess) {
        HttpStatusCode.OK to requireNotNull(getOrNull())
    } else {
        val throwable = requireNotNull(exceptionOrNull())
        HttpStatusCode.from(throwable) to Error(
            exception = throwable.stackTraceToString(),
            message = throwable.message.orEmpty()
        )
    }
}

private fun HttpStatusCode.Companion.from(e: Throwable): HttpStatusCode {
    return when (e) {
        is UnauthorizedException -> Unauthorized
        else -> InternalServerError
    }
}

internal suspend inline fun <reified T : Any> ApplicationCall.respond(result: Result<T>) {
    val (status, payload) = result.toResponse()
    respond(status = status, message = payload)
}
