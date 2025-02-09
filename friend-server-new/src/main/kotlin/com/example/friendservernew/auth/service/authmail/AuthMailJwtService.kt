package com.atelier.server.auth.service

import com.atelier.server.jwt.JwtType
import com.atelier.server.jwt.JwtUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AuthMailJwtService(
    private val jwtUtil: JwtUtil,
    @Value("\${auth.email-jwt-expiration}") private val emailJwtExpiration: Long,
) {
    fun createEmailVerifyToken(email: String): String =
        jwtUtil.createToken(
            "email" to email,
            "type" to JwtType.EMAIL_VERIFY,
            expirationSeconds = emailJwtExpiration,
        )
}
