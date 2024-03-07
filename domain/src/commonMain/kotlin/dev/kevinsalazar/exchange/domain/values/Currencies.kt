package dev.kevinsalazar.exchange.domain.values

import dev.kevinsalazar.exchange.domain.entities.Currency
import kotlinx.serialization.Serializable

@Serializable
class Currencies(
    val crypto: List<Currency>,
    val fiat: List<Currency>
)