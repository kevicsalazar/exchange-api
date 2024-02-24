package dev.kevinsalazar.exchange.domain.ports.driven

import dev.kevinsalazar.exchange.domain.entities.Balance

interface BalanceRepository {

    suspend fun findBalance(userId: String, currencyId: Int): Balance?
    suspend fun findAll(userId: String): List<Balance>
    suspend fun updateBalance(balance: Balance)

}