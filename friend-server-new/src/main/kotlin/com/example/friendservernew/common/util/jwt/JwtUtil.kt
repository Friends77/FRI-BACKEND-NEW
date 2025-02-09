package com.example.friendservernew.common.util.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil (
    @Value("\${jwt.secret-key}") private val secret: String,
){
    val secretKey: SecretKey =  Keys.hmacShaKeyFor(secret.toByteArray())

    private fun getDateAfterSeconds(seconds: Long) = Date(System.currentTimeMillis() + seconds * 1000)

    fun createToken(
        vararg claims: Pair<String, Any>,
        expirationSeconds: Long,
    ): String {
        val claimsMap = mapOf(*claims)

        return Jwts
            .builder()
            .apply {
                claimsMap.forEach { (key, value) -> claim(key, value) }
            }.signWith(secretKey)
            .expiration(getDateAfterSeconds(expirationSeconds))
            .compact()
    }

    fun <T> getClaim(
        token: String,
        key: String,
        type: Class<T>,
    ): T? =
        Jwts
            .parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
            .get(key, type)

    fun getExpiration(token: String): Date =
        Jwts
            .parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload.expiration

    fun isValid(token: String): Boolean =
        try {
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
}