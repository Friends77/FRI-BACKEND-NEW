package com.example.user.adapter.controller

import com.example.auth.application.service.AuthService
import com.example.user.adapter.AdapterMapper
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
@RequestMapping("/auth")
class AuthController (
    private val authService: AuthService
){
    @PostMapping("/register")
    fun register(@RequestBody registerRegisterDto: RegisterRequestDto) : ResponseEntity<RegisterResponseDto>{
        val registerDto = AdapterMapper.registerRequestDtoToRegisterDto(registerRegisterDto)
        val userDto = authService.register(registerDto)
        val registerResponseDto = AdapterMapper.userDtoToRegisterResponseDto(userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponseDto)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto) : ResponseEntity<LoginResponseDto>{
        val loginDto = AdapterMapper.loginRequestDtoToLoginDto(loginRequestDto)
        val atRtDto = authService.login(loginDto)
        val loginResponseDto = AdapterMapper.atRtDtoToLoginResponseDto(atRtDto)
        return ResponseEntity.ok(loginResponseDto)
    }
}