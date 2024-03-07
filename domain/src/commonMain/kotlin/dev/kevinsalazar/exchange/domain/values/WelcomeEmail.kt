package dev.kevinsalazar.exchange.domain.values

import dev.kevinsalazar.exchange.domain.enums.EmailTemplate
import kotlinx.serialization.Serializable

@Serializable
data class WelcomeEmail(
    val name: String,
    val template: EmailTemplate = EmailTemplate.Welcome
)