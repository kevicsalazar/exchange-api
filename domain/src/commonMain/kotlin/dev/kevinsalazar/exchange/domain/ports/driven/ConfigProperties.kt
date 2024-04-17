package dev.kevinsalazar.exchange.domain.ports.driven

sealed interface ConfigProperties {
    interface Jwt : ConfigProperties {
        val secret: String
        val issuer: String
        val audience: String
    }

    interface Database : ConfigProperties {
        val username: String
        val password: String
        val url: String
    }

    interface Remote : ConfigProperties {
        val apiKey: String
        val url: String
    }

    interface Events : ConfigProperties {
        val region: String
        val source: String
    }
}