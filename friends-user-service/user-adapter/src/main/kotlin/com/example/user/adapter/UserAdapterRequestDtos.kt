package com.example.user.adapter

data class RegisterRequestDto(
    val emailAuthToken: String,
    val nickname: String,
    val email: String,
    val password: String,
)

data class LoginRequestDto(
    val email: String,
    val password: String,
)