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

    fun isValidEmailAuthToken(
        emailAuthToken: String,
        email: String,
    ) : Boolean {
        val emailInToken = getEmail(emailAuthToken) ?: return false
        val typeStr = getType(emailAuthToken) ?: return false
        val type = try {
            JwtType.valueOf(typeStr)
        } catch (e: Exception) {
            return false
        }

        if (!jwtUtil.isValid(emailAuthToken)) {
            return false
        }
        if (type != JwtType.EMAIL_VERIFY) {
            return false
        }
        if (emailInToken != email) {
            return false
        }
        return true
    }

    private fun getEmail(token: String): String? = jwtUtil.getClaim(token, EMAIL, String::class.java)

    private fun getType(token: String): String? = jwtUtil.getClaim(token, TYPE, String::class.java)

}