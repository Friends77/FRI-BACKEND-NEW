package com.example.friendservernew.auth.service.authmail


import com.example.friendservernew.auth.controller.EmailAuthTokenResponseDto
import com.example.friendservernew.auth.service.CreateEmailAuthTokenDto
import com.example.friendservernew.auth.service.SendEmailVerifyCodeDto
import org.springframework.stereotype.Service

@Service
class AuthMailService(
    private val authMailJwtService: AuthMailJwtService,
    private val authMailSendService: AuthMailSendService,
) {
    fun sendEmailVerifyCode(sendEmailVerifyCodeDto: SendEmailVerifyCodeDto) {
        val email = sendEmailVerifyCodeDto.email
        authMailSendService.sendVerificationMailAsync(email)
    }

    fun createEmailAuthToken(createEmailAuthTokenDto: CreateEmailAuthTokenDto): EmailAuthTokenResponseDto {
        val email = createEmailAuthTokenDto.email
        val code = createEmailAuthTokenDto.code
        authMailSendService.verifyEmailCode(email, code)
        val emailAuthToken = authMailJwtService.createEmailVerifyToken(email)
        return EmailAuthTokenResponseDto(emailAuthToken)
    }
}
