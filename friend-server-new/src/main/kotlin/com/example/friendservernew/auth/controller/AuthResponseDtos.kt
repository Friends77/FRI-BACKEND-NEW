package com.example.friendservernew.auth.controller

data class RegisterResponseDto(
    val accessToken: String,
    val refreshToken: String,
)

data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String,
)

data class RefreshResponseDto(
    val accessToken: String,
    val refreshToken: String,
)

data class EmailAuthTokenResponseDto(
    val emailAuthToken: String
)