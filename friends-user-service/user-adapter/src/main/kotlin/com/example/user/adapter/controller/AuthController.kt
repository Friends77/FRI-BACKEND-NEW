package com.example.user.adapter.controller

import com.example.auth.application.dto.RefreshDto
import com.example.auth.application.service.UserLoginUseCase
import com.example.auth.application.service.UserPasswordUseCase
import com.example.auth.application.service.UserRegisterUseCase
import com.example.user.adapter.AdapterMapper
import com.example.user.adapter.LoginRequestDto
import com.example.user.adapter.LoginResponseDto
import com.example.user.adapter.RegisterRequestDto
import com.example.user.adapter.RegisterResponseDto
import com.example.user.adapter.ResetPasswordRequestDto
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
    private val userRegisterUseCase: UserRegisterUseCase,
    private val userLoginUseCase: UserLoginUseCase,
    private val userPasswordUseCase: UserPasswordUseCase
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

    @PostMapping("/reset-password")
    fun resetPassword(
        @RequestBody resetPasswordRequestDto: ResetPasswordRequestDto
    ) : ResponseEntity<String>{
        val resetPasswordDto = AdapterMapper.resetPasswordRequestDtoToResetPasswordDto(resetPasswordRequestDto)
        userPasswordUseCase.resetPassword(resetPasswordDto)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}