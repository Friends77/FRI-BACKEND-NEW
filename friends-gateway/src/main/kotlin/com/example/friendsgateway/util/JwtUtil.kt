package com.example.friendsgateway.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class JwtUtil (
    @Value("\${jwt.secret-key}") private val secret: String,
){
    val secretKey: SecretKey =  Keys.hmacShaKeyFor(secret.toByteArray())

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