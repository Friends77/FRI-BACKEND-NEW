package com.example.user.domain.service

import com.example.user.domain.util.JwtUtil
import com.example.user.domain.valueobject.JwtType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class EmailAuthTokenService(
    private val jwtUtil: JwtUtil,
    @Value("\${auth.email-jwt-expiration}") private val emailJwtExpiration: Long,
) {
    companion object {
        private const val EMAIL = "email"
        private const val TYPE = "type"
    }

    fun createEmailVerifyToken(email: String): String =
        jwtUtil.createToken(
            EMAIL to email,
            TYPE to JwtType.EMAIL_VERIFY,
            expirationSeconds = emailJwtExpiration,
        )

}