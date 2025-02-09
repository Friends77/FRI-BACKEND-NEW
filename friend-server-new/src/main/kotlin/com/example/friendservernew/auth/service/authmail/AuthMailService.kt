package com.example.friendservernew.auth.service.authmail


import com.example.friendservernew.auth.controller.EmailAuthTokenRequestDto
import com.example.friendservernew.auth.controller.EmailAuthTokenResponseDto
import com.example.friendservernew.auth.controller.EmailVerifyCodeRequestDto
import org.springframework.stereotype.Service

@Service
class AuthMailService(
    private val authMailJwtService: AuthMailJwtService,
    private val authMailSendService: AuthMailSendService,
) {
    fun sendEmailVerifyCode(emailVerifyCodeRequestDto: EmailVerifyCodeRequestDto) {
        val email = emailVerifyCodeRequestDto.email
        authMailSendService.sendVerificationMailAsync(email)
    }

    fun createEmailAuthToken(emailAuthTokenRequestDto: EmailAuthTokenRequestDto): EmailAuthTokenResponseDto {
        val email = emailAuthTokenRequestDto.email
        val code = emailAuthTokenRequestDto.code
        authMailSendService.verifyEmailCode(email, code)
        val emailAuthToken = authMailJwtService.createEmailVerifyToken(email)
        return EmailAuthTokenResponseDto(emailAuthToken)
    }
}
