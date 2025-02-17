package com.example.auth.application.service

import com.example.auth.application.dto.CreateEmailAuthTokenDto
import com.example.auth.application.dto.EmailAuthTokenDto
import com.example.user.domain.auth.EmailAuthTokenGenerator
import com.example.user.domain.auth.VerificationMailSender
import com.example.user.domain.validator.EmailVerificationCodeValidator
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutorService

@Service
class AuthMailUseCase(
    private val verificationMailSender: VerificationMailSender,
    private val emailVerificationCodeValidator: EmailVerificationCodeValidator,
    private val emailAuthTokenGenerator: EmailAuthTokenGenerator,
    private val executorService: ExecutorService
) {
    fun sendEmailVerifyCodeAsync(email : String) {
        executorService.submit {
            verificationMailSender.sendVerificationMail(email)
        }
    }

    fun createEmailAuthToken(createEmailAuthTokenDto: CreateEmailAuthTokenDto) : EmailAuthTokenDto {
        val email = createEmailAuthTokenDto.email
        val code = createEmailAuthTokenDto.code

        emailVerificationCodeValidator.validateEmailCode(email, code)

        val token = emailAuthTokenGenerator.createEmailAuthToken(email)
        return EmailAuthTokenDto(token)
    }
}