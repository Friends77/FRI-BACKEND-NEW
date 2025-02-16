package com.example.user.infrastructure.auth

import com.example.user.domain.validator.JwtValidator
import com.example.user.domain.valueobject.JwtType
import com.example.user.infrastructure.util.JwtClaimReaderImpl
import com.example.user.infrastructure.util.JwtUtil
import org.springframework.stereotype.Component

@Component
class JwtValidatorImpl(
    private val jwtUtil: JwtUtil,
    private val jwtClaimReaderImpl: JwtClaimReaderImpl
) : JwtValidator {
    override fun isValid(token: String, type: JwtType) : Boolean {
        if (!jwtUtil.isValid(token)) return false
        val tokenType = jwtClaimReaderImpl.getType(token) ?: return false
        return tokenType == type
    }
}