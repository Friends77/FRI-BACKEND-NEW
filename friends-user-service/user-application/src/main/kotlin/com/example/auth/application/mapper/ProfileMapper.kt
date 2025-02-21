package com.example.auth.application.mapper

import com.example.auth.application.dto.LocationDto
import com.example.auth.application.dto.ProfileDto
import com.example.user.domain.entity.Profile

object ProfileMapper {
    fun profileToProfileDto(profile: Profile) : ProfileDto {
        return ProfileDto(
            memberId = profile.memberId,
            profileId = profile.id,
            nickname = profile.nickname.value,
            birth = profile.birth?.localDate,
            gender = profile.gender?.type?.name,
            location = profile.location?.let { LocationDto(it.latitude, it.longitude) },
            mbti = profile.mbti?.type?.name,
            profileImageUrl = profile.image?.url,
            categories = profile.profileCategories.map { it.category.type.name },
            selfDescription = profile.selfDescription?.value
        )
    }
}