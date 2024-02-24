package dev.kevinsalazar.exchange.domain.utils

import java.time.Instant
import java.time.format.DateTimeFormatter

fun instantToString(instant: Instant): String {
    val formatter = DateTimeFormatter.ISO_INSTANT
    return formatter.format(instant)
}

fun stringToInstant(dateString: String): Instant {
    val formatter = DateTimeFormatter.ISO_INSTANT
    return Instant.from(formatter.parse(dateString))
}

fun getTimeStamp(): String {
    return instantToString(Instant.now())
}