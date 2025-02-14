package com.example.user.adapter

import java.util.UUID

data class RegisterResponseDto(
    val userId : UUID,
    val email: String,
)

data class LoginResponseDto(
    val accessToken : String,
    val refreshToken : String
)

data class EmailAuthTokenResponseDto(
    val emailAuthToken : String
)