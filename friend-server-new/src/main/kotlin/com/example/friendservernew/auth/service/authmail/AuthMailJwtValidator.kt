package com.atelier.server.auth.service

import com.atelier.server.jwt.JwtType
import com.atelier.server.jwt.JwtUtil
import com.example.friendservernew.auth.service.ValidateEmailAuthTokenDto
import com.example.friendservernew.auth.exception.InvalidJwtTokenException
import com.example.friendservernew.auth.exception.InvalidTokenTypeException
import com.example.friendservernew.auth.exception.MissingJwtPayloadException
import org.springframework.stereotype.Component

@Component
class AuthMailJwtValidator(
    private val jwtUtil: JwtUtil,
) {
    fun validateEmailAuthToken(validateEmailAuthTokenDto: ValidateEmailAuthTokenDto) {
        val token = validateEmailAuthTokenDto.token
        val email = validateEmailAuthTokenDto.email
        val emailInToken = getEmail(token)
        val type = getType(token)

        if (!jwtUtil.isValid(token)) {
            throw InvalidJwtTokenException()
        }
        if (type != JwtType.EMAIL_VERIFY) {
            throw InvalidTokenTypeException()
        }
        if (emailInToken != email) {
            throw InvalidJwtTokenException()
        }
    }

    private fun getEmail(token: String): String =
        jwtUtil.getClaim(token, "email", String::class.java) ?: throw MissingJwtPayloadException("email")

    private fun getType(token: String): JwtType {
        val type = jwtUtil.getClaim(token, "type", String::class.java) ?: throw MissingJwtPayloadException("type")
        return JwtType.valueOf(type)
    }
}
