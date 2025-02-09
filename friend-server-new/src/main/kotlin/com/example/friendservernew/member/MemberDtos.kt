package com.example.friendservernew.member

data class CreateUserDto(
    val nickname: String,
    val email: String,
    val encodedPassword: String,
)