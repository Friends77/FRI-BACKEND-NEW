package com.example.auth.application.service

import com.example.auth.application.dto.ProfileDto
import com.example.auth.application.mapper.ProfileMapper
import com.example.user.domain.service.ProfileQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class ProfileQueryUseCase(
    private val profileQueryService: ProfileQueryService
) {
    fun getMyProfile(memberId: UUID) : ProfileDto {
        val profile = profileQueryService.getProfile(memberId)
        return ProfileMapper.profileToProfileDto(profile)
    }
}