package dev.kevinsalazar.exchange.domain.errors

class UnauthorizedException(message: String? = null) : Exception(message ?: "Unauthorized")
class ServerException(message: String? = null) : Exception(message ?: "Internal Server Error")
class EmailAlreadyRegisterdException : Exception("Email already registered")
class InsufficientFundsException : Exception("Insufficient funds")