package com.example.user.domain.validator

import com.example.user.domain.exception.InvalidRefreshTokenException
import com.example.user.domain.valueobject.type.JwtType
import org.springframework.stereotype.Component

@Component
class AtRtValidator (
    private val jwtValidator: JwtValidator
){
    fun validateRefreshToken(refreshToken: String) {
        if (!jwtValidator.isValid(refreshToken, JwtType.REFRESH)) {
            throw InvalidRefreshTokenException()
        }
    }
}