package com.example.auth.application.dto

import java.time.LocalDate
import java.util.UUID

class ChangeNicknameDto(
    val memberId : UUID,
    val nickname : String
)

class ChangeBirthDto(
    val memberId : UUID,
    val localDate: LocalDate
)

class ChangeGenderDto(
    val memberId : UUID,
    val gender : String
)

class ChangeLocationDto(
    val memberId : UUID,
    val latitude : Double,
    val longitude : Double
)

class ChangeMbtiDto(
    val memberId : UUID,
    val mbti : String
)

class ChangeProfileImageDto(
    val memberId : UUID,
    val imageUrl : String
)

class ChangeCategoriesDto(
    val memberId : UUID,
    val categories : List<String>
)
