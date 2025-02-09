package com.example.friendservernew.auth.controller

data class RegisterRequestDto(
    val nickname: String,
    val email: String,
    val emailAuthToken: String,
    val password: String,
)

data class LoginRequestDto(
    val email: String,
    val password: String,
)

data class RefreshRequestDto(
    val refreshToken: String,
)

data class EmailVerifyCodeRequestDto(
    val email: String,
)

data class EmailAuthTokenRequestDto(
    val email: String,
    val code: String,
)
