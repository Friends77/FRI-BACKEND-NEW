package com.example.user.domain.util

interface JwtUtil {
    fun createToken(
        vararg claims: Pair<String, Any>,
        expirationSeconds: Long,
    ): String

    fun <T> getClaim(
        token: String,
        key: String,
        type: Class<T>,
    ): T?

    fun isValid(token: String): Boolean
}