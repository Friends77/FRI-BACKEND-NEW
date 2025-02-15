package com.example.auth.application.service

import com.example.auth.application.CreateEmailAuthTokenDto
import com.example.auth.application.EmailAuthTokenDto
import com.example.user.domain.service.EmailAuthTokenService
import com.example.user.domain.service.EmailVerificationService
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutorService

@Service
class AuthMailUseCase(
    private val emailVerificationService: EmailVerificationService,
    private val emailAuthTokenService: EmailAuthTokenService,
    private val executorService: ExecutorService
) {
    fun sendEmailVerifyCodeAsync(email : String) {
        executorService.submit {
            emailVerificationService.sendVerificationMail(email)
        }
    }

    fun createEmailAuthToken(createEmailAuthTokenDto: CreateEmailAuthTokenDto) : EmailAuthTokenDto {
        val email = createEmailAuthTokenDto.email
        val code = createEmailAuthTokenDto.code

        emailVerificationService.validateEmailCode(email, code)

        val token = emailAuthTokenService.createEmailVerifyToken(email)
        return EmailAuthTokenDto(token)
    }
}