package com.example.user.adapter

data class RegisterRequestDto(
    val nickname: String,
    val email: String,
    val password: String,
)