package com.example.user.domain.validator

import com.example.user.domain.exception.InvalidEmailJwtException
import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.util.JwtUtil
import com.example.user.domain.valueobject.JwtType
import org.springframework.stereotype.Component

@Component
class EmailAuthTokenValidator(
    private val jwtUtil: JwtUtil,
) {
    companion object {
        private const val EMAIL = "email"
        private const val TYPE = "type"
    }

    fun validateEmailAuthToken(
        emailAuthToken: String,
        email: String,
    ) {
        if (!jwtUtil.isValid(emailAuthToken)) throw InvalidEmailJwtException()

        val emailInToken = getEmail(emailAuthToken) ?: throw MissingJwtPayloadException(EMAIL)
        val typeStr = getType(emailAuthToken) ?: throw MissingJwtPayloadException(TYPE)
        val type = try {
            JwtType.valueOf(typeStr)
        } catch (e: Exception) {
            throw InvalidEmailJwtException()
        }

        if (type != JwtType.EMAIL_VERIFY || emailInToken != email) throw InvalidEmailJwtException()
    }

    private fun getEmail(token: String): String? = jwtUtil.getClaim(token, EMAIL, String::class.java)

    private fun getType(token: String): String? = jwtUtil.getClaim(token, TYPE, String::class.java)
}