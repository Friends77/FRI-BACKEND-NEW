package com.example.user.domain.validator

import com.example.user.domain.exception.InvalidEmailVerificationCodeException
import com.example.user.domain.repository.EmailVerificationCodeRepository
import org.springframework.stereotype.Component

@Component
class EmailVerificationCodeValidator(
    private val emailVerificationCodeRepository: EmailVerificationCodeRepository
) {
    fun validateEmailCode(
        email: String,
        code: String,
    ) {
        val savedCode = emailVerificationCodeRepository.getCode(email) ?: throw InvalidEmailVerificationCodeException()
        if (savedCode != code) {
            throw InvalidEmailVerificationCodeException()
        }
    }
}