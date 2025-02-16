package com.example.user.domain.validator

import com.example.user.domain.entity.auth.JwtClaimReader
import com.example.user.domain.exception.InvalidEmailJwtException
import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.JwtType
import org.springframework.stereotype.Component

@Component
class EmailAuthTokenValidator(
    private val jwtValidator: JwtValidator,
    private val jwtClaimReader: JwtClaimReader
) {

    fun validateEmailAuthToken(
        emailAuthToken: String,
        email: String,
    ) {
        if (!jwtValidator.isValid(emailAuthToken, JwtType.EMAIL_VERIFY)) throw InvalidEmailJwtException()

        val emailInToken = jwtClaimReader.getEmail(emailAuthToken) ?: throw MissingJwtPayloadException(JwtKey.EMAIL.value)

        if (emailInToken != email) throw InvalidEmailJwtException()
    }
}