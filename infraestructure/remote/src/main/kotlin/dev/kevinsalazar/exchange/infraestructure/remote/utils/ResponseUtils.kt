package dev.kevinsalazar.exchange.infraestructure.remote.utils

import dev.kevinsalazar.exchange.infraestructure.remote.dto.Response
import dev.kevinsalazar.exchange.domain.errors.ServerException
import dev.kevinsalazar.exchange.domain.errors.UnauthorizedException

fun <T> Response<T>.get(): T {
    if (data != null) {
        return data
    } else {
        throw status.toException()
    }
}

fun Response.Status.toException(): Exception {
    return when (errorCode) {
        400 -> UnauthorizedException(errorMessage)
        else -> ServerException(errorMessage)
    }
}