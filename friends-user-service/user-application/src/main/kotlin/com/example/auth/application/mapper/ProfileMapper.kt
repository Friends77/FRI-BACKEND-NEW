package com.example.auth.application.mapper

import com.example.auth.application.dto.LocationDto
import com.example.auth.application.dto.ProfileDto
import com.example.user.domain.dao.data.ProfileView
import com.example.user.domain.entity.Profile
import com.fasterxml.jackson.databind.ObjectMapper

object ProfileMapper {
    fun profileViewToProfileDto(profileView: ProfileView) : ProfileDto {
        return ProfileDto(
            memberId = profileView.memberId,
            profileId = profileView.profileId,
            nickname = profileView.nickname,
            birth = profileView.birth,
            gender = profileView.gender,
            location = if (profileView.latitude != null && profileView.longitude != null ) {
                LocationDto(
                    latitude = profileView.latitude!!, // 다른 모듈에 있는 공개 프로퍼티는 스마트 캐스팅이 안됨
                    longitude = profileView.longitude!!
                )
            } else {
                null
            },
            mbti = profileView.mbti,
            profileImageUrl = profileView.imageUrl,
            categories = profileView.categories,
            selfDescription = profileView.selfDescription,
        )
    }

    fun profileToProfileDto(profile: Profile) : ProfileDto {
        return ProfileDto(
            memberId = profile.memberId,
            profileId = profile.id,
            nickname = profile.nickname.value,
            birth = profile.birth?.localDate,
            gender = profile.gender?.type?.name,
            location = if (profile.location != null) {
                LocationDto(
                    latitude = profile.location!!.latitude,
                    longitude = profile.location!!.longitude
                )
            } else {
                null
            },
            mbti = profile.mbti?.type?.name,
            profileImageUrl = profile.image?.url,
            categories = profile.profileCategories.map { it.category.type.name },
            selfDescription = profile.selfDescription?.value
        )
    }
}