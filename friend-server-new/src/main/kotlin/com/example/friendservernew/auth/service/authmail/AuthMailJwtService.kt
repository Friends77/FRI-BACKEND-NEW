package com.example.friendservernew.auth.service.authmail

import com.example.friendservernew.common.util.jwt.JwtType
import com.example.friendservernew.common.util.jwt.JwtUtil
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
