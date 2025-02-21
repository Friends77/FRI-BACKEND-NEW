package com.example.auth.application.dto

import java.time.LocalDate
import java.util.UUID

data class ChangeNicknameDto(
    val memberId : UUID,
    val nickname : String
)

data class ChangeBirthDto(
    val memberId : UUID,
    val localDate: LocalDate
)

data class ChangeGenderDto(
    val memberId : UUID,
    val gender : String
)

data class ChangeLocationDto(
    val memberId : UUID,
    val latitude : Double,
    val longitude : Double
)

data class ChangeMbtiDto(
    val memberId : UUID,
    val mbti : String
)

data class ChangeProfileImageDto(
    val memberId : UUID,
    val imageUrl : String?
)

data class ChangeCategoriesDto(
    val memberId : UUID,
    val categories : List<String>
)

data class ChangeSelfDescriptionDto(
    val memberId : UUID,
    val selfDescription : String
)

data class ProfileDto(
    val memberId : UUID,
    val profileId : UUID,
    val nickname : String,
    val birth : LocalDate?,
    val gender: String?,
    val location : LocationDto?,
    val mbti : String?,
    val profileImageUrl : String?,
    val categories : List<String>,
    val selfDescription : String?
)

data class LocationDto(
    val latitude : Double,
    val longitude : Double
)