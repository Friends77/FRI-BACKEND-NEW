package com.example.user.domain.validator

import com.example.user.domain.exception.InvalidRefreshTokenException
import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.util.JwtUtil
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.JwtType
import org.springframework.stereotype.Component

@Component
class AtRtValidator (
    private val jwtUtil: JwtUtil,
){
    fun validateRefreshToken(refreshToken: String) {
        if (!jwtUtil.isValid(refreshToken) || getType(refreshToken) != JwtType.REFRESH) {
            throw InvalidRefreshTokenException()
        }
    }

    private fun getType(token : String) : JwtType {
        val typeStr = jwtUtil.getClaim(token, JwtKey.TYPE.value, String::class.java)
            ?: throw MissingJwtPayloadException(JwtKey.TYPE.value)
        return try {
            JwtType.valueOf(typeStr)
        } catch (e: Exception) {
            throw MissingJwtPayloadException(JwtKey.TYPE.value)
        }
    }
}