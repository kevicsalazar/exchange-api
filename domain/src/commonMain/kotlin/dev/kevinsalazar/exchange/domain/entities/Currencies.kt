package dev.kevinsalazar.exchange.domain.entities

import kotlinx.serialization.Serializable

@Serializable
class Currencies(
    val crypto: List<Currency>,
    val fiat: List<Currency>
)