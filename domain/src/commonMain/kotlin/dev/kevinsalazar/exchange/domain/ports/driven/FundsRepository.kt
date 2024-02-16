package dev.kevinsalazar.exchange.domain.ports.driven

interface FundsRepository {

    suspend fun findfunds(userId: String, currencyId: Int): Float
    suspend fun updateFunds(userId: String, currencyId: Int, amount: Float)

}