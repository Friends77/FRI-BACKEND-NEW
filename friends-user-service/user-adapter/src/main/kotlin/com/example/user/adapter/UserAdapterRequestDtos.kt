package com.example.user.adapter

data class RegisterRequestDto(
    val email: String,
    val password: String,
)

data class LoginRequestDto(
    val email: String,
    val password: String,
)