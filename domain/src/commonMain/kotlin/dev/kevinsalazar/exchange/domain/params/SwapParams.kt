package dev.kevinsalazar.exchange.domain.params

import kotlinx.serialization.Serializable

@Serializable
data class SwapParams(
    val userId: String,
    val from: Item,
    val to: Item
) {
    @Serializable
    data class Item(
        val currencyId: Int,
        val amount: Float,
    )
}
