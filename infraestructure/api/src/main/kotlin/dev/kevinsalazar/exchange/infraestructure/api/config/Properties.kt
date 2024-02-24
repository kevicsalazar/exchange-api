package dev.kevinsalazar.exchange.infraestructure.api.config

import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import io.ktor.server.application.*


class JwtConfigProperties(
    env: ApplicationEnvironment
) : ConfigProperties.Jwt {
    override val secret = env.getConfigProperty("jwt.secret")
    override val issuer = env.getConfigProperty("jwt.issuer")
    override val audience = env.getConfigProperty("jwt.audience")
}

class DatabaseConfigProperties(
    env: ApplicationEnvironment
) : ConfigProperties.Database {
    override val username = env.getConfigProperty("db.username")
    override val password = env.getConfigProperty("db.password")
    override val url = env.getConfigProperty("db.url")
}

class RemoteConfigProperties(
    env: ApplicationEnvironment
) : ConfigProperties.Remote {
    override val apiKey = env.getConfigProperty("remote.apiKey")
    override val url = env.getConfigProperty("remote.url")
}

private fun ApplicationEnvironment.getConfigProperty(path: String): String {
    return config.property(path).getString()
}
