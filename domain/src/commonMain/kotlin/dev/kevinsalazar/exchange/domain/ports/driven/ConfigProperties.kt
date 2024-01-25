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
        val databaseName: String
        val portNumber: String
        val serverName: String
    }
}