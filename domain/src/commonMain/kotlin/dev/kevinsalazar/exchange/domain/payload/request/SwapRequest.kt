package dev.kevinsalazar.exchange.domain.payload.request

import kotlinx.serialization.Serializable

@Serializable
data class SwapRequest(
    val userId: String,
    val sender: Item,
    val recipient: Item
) {
    @Serializable
    data class Item(
        val currencyId: Int,
        val amount: Float,
    )
}
