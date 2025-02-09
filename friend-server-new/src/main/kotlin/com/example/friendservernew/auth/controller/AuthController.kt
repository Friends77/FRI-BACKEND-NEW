package com.example.friendservernew.auth.controller

import com.example.friendservernew.auth.service.AuthMapper
import com.example.friendservernew.auth.service.AuthService
import com.example.friendservernew.auth.service.authmail.AuthMailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/global/auth")
class AuthController(
    private val authService: AuthService,
    private val authMailService: AuthMailService,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody registerRequestDto: RegisterRequestDto,
    ): ResponseEntity<String> {
        authService.register(registerRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공")
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequestDto: LoginRequestDto,
    ): ResponseEntity<LoginResponseDto> {
        val loginResponseDto = authService.login(loginRequestDto)
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody refreshRequestDto: RefreshRequestDto,
    ): ResponseEntity<RefreshResponseDto> {
        val refreshResponseDto = authService.refresh(refreshRequestDto)
        return ResponseEntity.status(HttpStatus.OK).body(refreshResponseDto)
    }

    @PostMapping("/email/send-verification")
    fun sendEmailVerifyCode(
        @RequestBody emailVerifyCodeRequestDto: EmailVerifyCodeRequestDto,
    ): ResponseEntity<String> {
        authMailService.sendEmailVerifyCode(emailVerifyCodeRequestDto)
        return ResponseEntity.status(HttpStatus.OK).body("이메일 인증 코드 전송 성공")
    }

    @PostMapping("/email/verify-code")
    fun verifyEmailCode(
        @RequestBody emailAuthTokenRequestDto: EmailAuthTokenRequestDto,
    ): ResponseEntity<EmailAuthTokenResponseDto> {
        val createEmailAuthToken = authMailService.createEmailAuthToken(emailAuthTokenRequestDto)
        return ResponseEntity.status(HttpStatus.OK).body(createEmailAuthToken)
    }
}