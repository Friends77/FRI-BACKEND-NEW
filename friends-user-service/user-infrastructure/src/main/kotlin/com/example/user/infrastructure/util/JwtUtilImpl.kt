package com.example.user.infrastructure.util

import com.example.user.domain.util.JwtUtil
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtilImpl (
    @Value("\${jwt.secret-key}") private val secret: String,
) : JwtUtil {
    private val secretKey: SecretKey =  Keys.hmacShaKeyFor(secret.toByteArray())

    private fun getDateAfterSeconds(seconds: Long) = Date(System.currentTimeMillis() + seconds * 1000)

    override fun createToken(
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

    override fun <T> getClaim(
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

    override fun isValid(token: String): Boolean =
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