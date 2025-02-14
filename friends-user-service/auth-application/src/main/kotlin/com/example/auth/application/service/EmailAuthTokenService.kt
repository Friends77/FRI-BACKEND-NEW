package com.example.auth.application.service

import com.example.auth.application.ValidateEmailAuthTokenDto
import com.example.auth.application.exception.InvalidEmailJwtException
import com.example.auth.application.exception.MissingJwtPayloadException
import com.example.auth.application.util.jwt.JwtType
import com.example.auth.application.util.jwt.JwtUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class EmailAuthTokenService(
    private val jwtUtil: JwtUtil,
    @Value("\${auth.email-jwt-expiration}") private val emailJwtExpiration: Long,
) {
    fun createEmailVerifyToken(email: String): String =
        jwtUtil.createToken(
            "email" to email,
            "type" to JwtType.EMAIL_VERIFY,
            expirationSeconds = emailJwtExpiration,
        )

    fun validateEmailAuthToken(validateEmailAuthTokenDto: ValidateEmailAuthTokenDto) {
        val token = validateEmailAuthTokenDto.token
        val email = validateEmailAuthTokenDto.email
        val emailInToken = getEmail(token)
        val type = getType(token)

        if (!jwtUtil.isValid(token)) {
            throw InvalidEmailJwtException()
        }
        if (type != JwtType.EMAIL_VERIFY) {
            throw InvalidEmailJwtException()
        }
        if (emailInToken != email) {
            throw InvalidEmailJwtException()
        }
    }

    private fun getEmail(token: String): String =
        jwtUtil.getClaim(token, "email", String::class.java) ?: throw MissingJwtPayloadException("email")

    private fun getType(token: String): JwtType {
        val type = jwtUtil.getClaim(token, "type", String::class.java) ?: throw MissingJwtPayloadException("type")
        return JwtType.valueOf(type)
    }
}