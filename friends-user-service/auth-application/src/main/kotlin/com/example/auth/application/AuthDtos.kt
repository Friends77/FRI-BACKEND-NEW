package com.example.auth.application

import java.util.UUID

data class AtRtDto(
    val accessToken : String,
    val refreshToken : String
)

data class RegisterDto(
    val emailAuthToken : String,
    val nickname: String,
    val email : String,
    val password : String
)

data class UserDto(
    val memberId : UUID,
    val email : String
)

data class LoginDto(
    val email : String,
    val password : String
)

data class OAuth2LoginDto(
    val authorizationCode : String,
    val oAuth2Provider: String,
)

data class ValidateEmailAuthTokenDto(
    val token : String,
    val email : String
)

data class CreateEmailAuthTokenDto(
    val email : String,
    val code : String
)

data class EmailAuthTokenDto(
    val emailAuthToken : String
)

data class RefreshDto(
    val refreshToken : String
)