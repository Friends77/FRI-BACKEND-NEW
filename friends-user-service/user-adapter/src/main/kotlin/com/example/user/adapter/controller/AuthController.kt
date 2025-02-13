package com.example.user.adapter.controller

import com.example.auth.application.service.AuthService
import com.example.user.adapter.AdapterMapper
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
    private val authService: com.example.auth.application.service.AuthService
){
    @PostMapping("/register")
    fun register(@RequestBody registerRegisterDto: RegisterRequestDto) : ResponseEntity<RegisterResponseDto>{
        val registerDto = AdapterMapper.registerRequestDtoToRegisterDto(registerRegisterDto)
        val userDto = authService.register(registerDto)
        val registerResponseDto = AdapterMapper.userDtoToRegisterResponseDto(userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponseDto)
    }
}