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
        val queues: List<Queue>
        val region: String

        data class Queue(
            val name: String,
            val url: String
        )
    }
}