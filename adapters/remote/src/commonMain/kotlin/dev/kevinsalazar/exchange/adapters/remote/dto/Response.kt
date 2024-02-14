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
        @SerialName("timestamp") val timestamp: String,
        @SerialName("error_code") val errorCode: String,
        @SerialName("error_message") val errorMessage: String?,
        @SerialName("elapsed") val elapsed: String,
        @SerialName("credit_count") val creditCount: String,
        @SerialName("notice") val notice: String?,
    )
}