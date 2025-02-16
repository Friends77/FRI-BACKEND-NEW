package com.example.user.adapter

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

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

data class OAuth2LoginRequestDto(
    val authorizationCode : String,
    val oauth2Provider: String
)

data class EmailVerifyCodeRequestDto(
    val email: String,
)

data class EmailAuthTokenRequestDto(
    val email: String,
    val code: String,
)

data class ResetPasswordRequestDto(
    val emailAuthToken : String,
    val email: String,
    val newPassword: String,
)

data class ChangeNicknameRequestDto(
    val nickname : String
)

data class ChangeBirthRequestDto(
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val localDate: LocalDate
)

data class ChangeGenderRequestDto(
    val gender : String
)

data class ChangeLocationRequestDto(
    val latitude : Double,
    val longitude : Double
)

data class ChangeMbtiRequestDto(
    val mbti : String
)

data class ChangeProfileImageRequestDto(
    val imageUrl : String
)

data class ChangeCategoriesRequestDto(
    val categories : List<String>
)