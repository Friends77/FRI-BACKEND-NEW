package com.example.user.adapter.controller

import com.example.auth.application.service.AuthMailService
import com.example.user.adapter.AdapterMapper
import com.example.user.adapter.EmailAuthTokenRequestDto
import com.example.user.adapter.EmailAuthTokenResponseDto
import com.example.user.adapter.EmailVerifyCodeRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/global/auth/email")
class EmailAuthController (
    private val authMailService: AuthMailService,
) {
    @PostMapping("/send-verify-code")
    fun sendEmailVerifyCode(@RequestBody emailVerifyCodeRequestDto: EmailVerifyCodeRequestDto) : ResponseEntity<String> {
        val email = emailVerifyCodeRequestDto.email
        authMailService.sendEmailVerifyCodeAsync(email)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("이메일로 인증 코드를 전송했습니다.")
    }

    @PostMapping("/verify-code")
    fun verifyEmailCode(@RequestBody emailAuthTokenRequestDto: EmailAuthTokenRequestDto) : ResponseEntity<EmailAuthTokenResponseDto> {
        val createEmailAuthTokenDto =
            AdapterMapper.emailAuthTokenRequestDtoToCreateEmailAuthTokenDto(emailAuthTokenRequestDto)
        val emailAuthToken = authMailService.createEmailAuthToken(createEmailAuthTokenDto)
        val emailAuthTokenResponseDto = EmailAuthTokenResponseDto(emailAuthToken.emailAuthToken)
        return ResponseEntity.ok(emailAuthTokenResponseDto)
    }
}