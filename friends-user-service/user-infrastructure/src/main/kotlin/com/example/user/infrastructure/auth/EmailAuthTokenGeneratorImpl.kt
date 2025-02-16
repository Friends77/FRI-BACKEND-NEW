package com.example.user.infrastructure.auth

import com.example.user.domain.entity.auth.EmailAuthTokenGenerator
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.JwtType
import com.example.user.infrastructure.util.JwtUtilImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EmailAuthTokenGeneratorImpl(
    private val jwtUtilImpl: JwtUtilImpl,
    @Value("\${auth.email-jwt-expiration}") private val emailJwtExpiration: Long,
) : EmailAuthTokenGenerator {
    override fun createEmailAuthToken(email: String): String =
        jwtUtilImpl.createToken(
            JwtKey.EMAIL.value to email,
            JwtKey.TYPE.value to JwtType.EMAIL_VERIFY,
            expirationSeconds = emailJwtExpiration,
        )
}