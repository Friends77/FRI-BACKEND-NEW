package com.example.user.adapter

import java.util.UUID

data class RegisterResponseDto(
    val userId : UUID,
    val nickname: String,
    val email: String,
)