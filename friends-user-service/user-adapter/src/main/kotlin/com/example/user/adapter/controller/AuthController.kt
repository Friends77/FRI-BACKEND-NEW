package com.example.user.adapter.controller

import com.example.auth.application.service.AuthMailService
import com.example.auth.application.service.LoginService
import com.example.auth.application.service.UserRegisterUseCase
import com.example.user.adapter.AdapterMapper
import com.example.user.adapter.EmailAuthTokenRequestDto
import com.example.user.adapter.EmailAuthTokenResponseDto
import com.example.user.adapter.EmailVerifyCodeRequestDto
import com.example.user.adapter.LoginRequestDto
import com.example.user.adapter.LoginResponseDto
import com.example.user.adapter.RegisterRequestDto
import com.example.user.adapter.RegisterResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/global/auth")
class AuthController (
    private val authMailService: AuthMailService,
    private val userRegisterUseCase: UserRegisterUseCase,
    private val loginService: LoginService
){
    @PostMapping("/register")
    fun register(@RequestBody registerRegisterDto: RegisterRequestDto) : ResponseEntity<RegisterResponseDto>{
        val registerDto = AdapterMapper.registerRequestDtoToRegisterDto(registerRegisterDto)
        val userDto = userRegisterUseCase.register(registerDto)
        val registerResponseDto = AdapterMapper.userDtoToRegisterResponseDto(userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponseDto)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto) : ResponseEntity<LoginResponseDto>{
        val loginDto = AdapterMapper.loginRequestDtoToLoginDto(loginRequestDto)
        val atRtDto = loginService.login(loginDto)
        val loginResponseDto = AdapterMapper.atRtDtoToLoginResponseDto(atRtDto)
        return ResponseEntity.ok(loginResponseDto)
    }

    @PostMapping("/send-email-verify-code")
    fun sendEmailVerifyCode(@RequestBody emailVerifyCodeRequestDto: EmailVerifyCodeRequestDto) : ResponseEntity<String>{
        val email = emailVerifyCodeRequestDto.email
        authMailService.sendEmailVerifyCode(email)
        return ResponseEntity.ok("이메일 인증 코드 전송 (비동기)")
    }

    @PostMapping("/verify-email-code")
    fun verifyEmailCode(@RequestBody emailAuthTokenRequestDto: EmailAuthTokenRequestDto) : ResponseEntity<EmailAuthTokenResponseDto>{
        val createEmailAuthTokenDto =
            AdapterMapper.emailAuthTokenRequestDtoToCreateEmailAuthTokenDto(emailAuthTokenRequestDto)
        val emailAuthToken = authMailService.createEmailAuthToken(createEmailAuthTokenDto)
        val emailAuthTokenResponseDto = EmailAuthTokenResponseDto(emailAuthToken.emailAuthToken)
        return ResponseEntity.ok(emailAuthTokenResponseDto)
    }
}