package dev.kevinsalazar.exchange.application.utils

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UtilsTest : StringSpec({
    "generated uuid length should be 32" {
        val uuid = generateUUID()
        uuid.length shouldBe 36
    }
    "generated salt length should be 32" {
        val salt = generateRandomSalt()
        salt.length shouldBe 32
    }
    "generated hash should return the same hash" {
        val salt = "197375a5f149f89e979f281877705645"
        val hash = generateHash("pwd", salt)
        hash shouldBe "d1eaa5cc5ed9ac97fd946b6b9a67ea4ab46896645d317fa36a2bed5ad5382b43"
    }
})