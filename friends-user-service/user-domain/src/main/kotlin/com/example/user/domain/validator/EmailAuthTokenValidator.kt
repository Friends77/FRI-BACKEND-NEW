package com.example.user.domain.validator

import com.example.user.domain.exception.InvalidEmailJwtException
import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.util.JwtUtil
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.JwtType
import org.springframework.stereotype.Component

@Component
class EmailAuthTokenValidator(
    private val jwtUtil: JwtUtil,
) {

    fun validateEmailAuthToken(
        emailAuthToken: String,
        email: String,
    ) {
        if (!jwtUtil.isValid(emailAuthToken)) throw InvalidEmailJwtException()

        val emailInToken = getEmail(emailAuthToken) ?: throw MissingJwtPayloadException(JwtKey.EMAIL.value)
        val typeStr = getType(emailAuthToken) ?: throw MissingJwtPayloadException(JwtKey.TYPE.value)
        val type = try {
            JwtType.valueOf(typeStr)
        } catch (e: Exception) {
            throw InvalidEmailJwtException()
        }

        if (type != JwtType.EMAIL_VERIFY || emailInToken != email) throw InvalidEmailJwtException()
    }

    private fun getEmail(token: String): String? = jwtUtil.getClaim(token, JwtKey.EMAIL.value, String::class.java)

    private fun getType(token: String): String? = jwtUtil.getClaim(token, JwtKey.TYPE.value, String::class.java)
}