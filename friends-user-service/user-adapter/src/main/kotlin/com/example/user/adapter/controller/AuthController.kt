package com.example.user.adapter.controller

import com.example.auth.application.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.RefreshDto
import com.example.auth.application.service.AuthMailUseCase
import com.example.auth.application.service.UserLoginUseCase
import com.example.auth.application.service.UserOAuth2LoginUserCase
import com.example.auth.application.service.UserRegisterUseCase
import com.example.user.adapter.AdapterMapper
import com.example.user.adapter.EmailAuthTokenRequestDto
import com.example.user.adapter.EmailAuthTokenResponseDto
import com.example.user.adapter.EmailVerifyCodeRequestDto
import com.example.user.adapter.LoginRequestDto
import com.example.user.adapter.LoginResponseDto
import com.example.user.adapter.OAuth2LoginRequestDto
import com.example.user.adapter.RegisterRequestDto
import com.example.user.adapter.RegisterResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/global/auth")
class AuthController (
    private val authMailUseCase: AuthMailUseCase,
    private val userRegisterUseCase: UserRegisterUseCase,
    private val userLoginUseCase: UserLoginUseCase,
    private val userOAuth2LoginUserCase: UserOAuth2LoginUserCase
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
        val atRtDto = userLoginUseCase.login(loginDto)
        val loginResponseDto = AdapterMapper.atRtDtoToLoginResponseDto(atRtDto)
        return ResponseEntity.ok(loginResponseDto)
    }

    @PostMapping("/refresh")
    fun refresh(
        @CookieValue("refreshToken") refreshToken: String
    ) : ResponseEntity<LoginResponseDto>{
        val refreshDto = RefreshDto(refreshToken)
        val atRtDto = userLoginUseCase.refresh(refreshDto)
        val loginResponseDto = AdapterMapper.atRtDtoToLoginResponseDto(atRtDto)
        return ResponseEntity.ok(loginResponseDto)
    }

    @PostMapping("/oauth2-login")
    fun oauth2Login(@RequestBody oauth2LoginRequestDto: OAuth2LoginRequestDto) : ResponseEntity<LoginResponseDto> {
        val oAuth2LoginDto = AdapterMapper.oauth2LoginRequestDtoToOAuth2LoginDto(oauth2LoginRequestDto)
        val atRtDto = userOAuth2LoginUserCase.loginByOAuth2(oAuth2LoginDto)
        val loginResponseDto = AdapterMapper.atRtDtoToLoginResponseDto(atRtDto)
        return ResponseEntity.ok(loginResponseDto)
    }

    @PostMapping("/send-email-verify-code")
    fun sendEmailVerifyCode(@RequestBody emailVerifyCodeRequestDto: EmailVerifyCodeRequestDto) : ResponseEntity<String>{
        val email = emailVerifyCodeRequestDto.email
        authMailUseCase.sendEmailVerifyCodeAsync(email)
        return ResponseEntity.ok("이메일 인증 코드 전송 (비동기)")
    }

    @PostMapping("/verify-email-code")
    fun verifyEmailCode(@RequestBody emailAuthTokenRequestDto: EmailAuthTokenRequestDto) : ResponseEntity<EmailAuthTokenResponseDto>{
        val createEmailAuthTokenDto =
            AdapterMapper.emailAuthTokenRequestDtoToCreateEmailAuthTokenDto(emailAuthTokenRequestDto)
        val emailAuthToken = authMailUseCase.createEmailAuthToken(createEmailAuthTokenDto)
        val emailAuthTokenResponseDto = EmailAuthTokenResponseDto(emailAuthToken.emailAuthToken)
        return ResponseEntity.ok(emailAuthTokenResponseDto)
    }
}