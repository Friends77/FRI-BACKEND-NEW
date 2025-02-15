package com.example.user.domain.service

import com.example.user.domain.exception.InvalidEmailJwtException
import com.example.user.domain.exception.MissingJwtPayloadException
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

    fun validateEmailAuthToken(
        emailAuthToken: String,
        email: String,
    ) {

        val emailInToken = getEmail(emailAuthToken) ?: throw MissingJwtPayloadException(EMAIL)
        val typeStr = getType(emailAuthToken) ?: throw MissingJwtPayloadException(TYPE)
        val type = try {
            JwtType.valueOf(typeStr)
        } catch (e: Exception) {
            throw InvalidEmailJwtException()
        }

        if (!jwtUtil.isValid(emailAuthToken) || type != JwtType.EMAIL_VERIFY || emailInToken != email) throw InvalidEmailJwtException()
    }

    private fun getEmail(token: String): String? = jwtUtil.getClaim(token, EMAIL, String::class.java)

    private fun getType(token: String): String? = jwtUtil.getClaim(token, TYPE, String::class.java)

}