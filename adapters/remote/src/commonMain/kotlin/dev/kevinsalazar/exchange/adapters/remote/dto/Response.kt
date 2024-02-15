package dev.kevinsalazar.exchange.adapters.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Response<T>(
    @SerialName("data") val data: T? = null,
    @SerialName("status") val status: Status
) {
    @Serializable
    data class Status(
        val timestamp: String,
        @SerialName("error_code") val errorCode: Int,
        @SerialName("error_message") val errorMessage: String?,
        val elapsed: Int,
        @SerialName("credit_count") val creditCount: Int,
        val notice: String?
    )
}