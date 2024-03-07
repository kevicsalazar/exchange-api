package dev.kevinsalazar.exchange.domain.values

import dev.kevinsalazar.exchange.domain.enums.EmailTemplate
import kotlinx.serialization.Serializable

@Serializable
data class SwapEmail(
    val name: String,
    val sentAmount: String,
    val receivedAmount: String,
    val template: EmailTemplate = EmailTemplate.Swap
)