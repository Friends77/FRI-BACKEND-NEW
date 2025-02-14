package com.example.auth.application.service

import com.example.auth.application.CreateEmailAuthTokenDto
import com.example.auth.application.EmailAuthTokenDto
import org.springframework.stereotype.Service

@Service
class AuthMailService(
    private val emailVerificationService: EmailVerificationService,
    private val emailAuthTokenService: EmailAuthTokenService
) {
    fun sendEmailVerifyCode(email : String) {
        emailVerificationService.sendVerificationMailAsync(email)
    }

    fun createEmailAuthToken(createEmailAuthTokenDto: CreateEmailAuthTokenDto) : EmailAuthTokenDto{
        val email = createEmailAuthTokenDto.email
        val code = createEmailAuthTokenDto.code
        emailVerificationService.verifyEmailCode(email, code)
        val token = emailAuthTokenService.createEmailVerifyToken(email)
        return EmailAuthTokenDto(token)
    }
}