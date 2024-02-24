package dev.kevinsalazar.exchange.infraestructure.persist.repository

import dev.kevinsalazar.exchange.domain.ports.driven.FundsRepository

internal class DefaultFundsRepository : FundsRepository {

    override suspend fun findfunds(userId: String, currencyId: Int): Float {
        return 1_000_000f
    }

    override suspend fun updateFunds(userId: String, currencyId: Int, amount: Float) {

    }
}