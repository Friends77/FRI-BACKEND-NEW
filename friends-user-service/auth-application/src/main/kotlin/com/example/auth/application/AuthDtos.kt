package com.example.auth.application

import org.springframework.security.core.GrantedAuthority
import java.util.UUID

data class CreateAtRtDto(
    val memberId : UUID,
    val authorities : Collection<GrantedAuthority>
)

data class AtRtDto(
    val accessToken : String,
    val refreshToken : String
)

data class RegisterDto(
    val nickname: String,
    val email : String,
    val password : String
)

data class UserDto(
    val memberId : UUID,
    val nickname : String,
    val email : String
)

data class LoginDto(
    val email : String,
    val password : String
)