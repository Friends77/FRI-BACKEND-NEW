package com.example.user.adapter

data class RegisterRequestDto(
    val nickname: String,
    val email: String,
    val password: String,
)

data class LoginRequestDto(
    val email: String,
    val password: String,
)