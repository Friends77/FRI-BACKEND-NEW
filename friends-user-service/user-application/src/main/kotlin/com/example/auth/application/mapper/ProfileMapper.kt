package com.example.auth.application.mapper

import com.example.auth.application.dto.LocationDto
import com.example.auth.application.dto.ProfileDto
import com.example.user.domain.entity.Profile

object ProfileMapper {
    fun profileToProfileDto(profile: Profile) : ProfileDto {
        return ProfileDto(
            memberId = profile.id,
            nickname = profile.nickname,
            birth = profile.birth?.localDate,
            gender = profile.gender?.name,
            location = profile.location?.let { LocationDto(it.latitude, it.longitude) },
            mbti = profile.mbti?.name,
            profileImageUrl = profile.image,
            categories = profile.profileCategories.map { it.categorySubType.name },
            selfDescription = profile.selfDescription
        )
    }
}