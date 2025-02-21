package com.example.auth.application.mapper

import com.example.auth.application.dto.LocationDto
import com.example.auth.application.dto.ProfileDto
import com.example.user.domain.dao.data.ProfileView

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
                    latitude = profileView.latitude!!, // view 가 entity 로 선언되면서 open class 되어 스마트 캐스팅이 되지 않았다.
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
}