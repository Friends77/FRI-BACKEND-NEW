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

data class EmailVerifyCodeRequestDto(
    val email: String,
)

data class EmailAuthTokenRequestDto(
    val email: String,
    val code: String,
)