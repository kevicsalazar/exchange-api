package dev.kevinsalazar.exchange.domain.values

import kotlinx.serialization.Serializable

@Serializable
data class SwapEmail(
    val name: String,
    val sentAmount: String,
    val receivedAmount: String
) {
    companion object {
        const val TEMPLATE = "Swap"
    }
}