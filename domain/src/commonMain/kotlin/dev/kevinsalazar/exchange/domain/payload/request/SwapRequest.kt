package dev.kevinsalazar.exchange.domain.payload.request

import kotlinx.serialization.Serializable

@Serializable
data class SwapRequest(
    val send: Item,
    val receive: Item
) {
    @Serializable
    data class Item(
        val currencyCode: String,
        val amount: Double,
    )
}
