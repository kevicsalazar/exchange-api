package dev.kevinsalazar.exchange.server.utils

import dev.kevinsalazar.exchange.domain.ports.driven.ConfigProperties
import io.ktor.server.application.*

class AppConfigProperties(
    private val application: Application
) {

    val jwt = object : ConfigProperties.Jwt {
        override val secret = getConfigProperty("jwt.secret")
        override val issuer = getConfigProperty("jwt.issuer")
        override val audience = getConfigProperty("jwt.audience")
    }

    val database = object : ConfigProperties.Database {
        override val username = getConfigProperty("db.username")
        override val password = getConfigProperty("db.password")
        override val url = getConfigProperty("db.url")
    }

    val remote = object : ConfigProperties.Remote {
        override val apiKey= getConfigProperty("remote.apiKey")
        override val url= getConfigProperty("remote.url")
    }

    private fun getConfigProperty(path: String): String {
        return application.environment.config.property(path).getString()
    }

    companion object {

        @Volatile
        private lateinit var instance: AppConfigProperties

        fun initialize(application: Application) {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = AppConfigProperties(application)
                }
            }
        }

        fun getInstance(): AppConfigProperties {
            return instance
        }
    }

}