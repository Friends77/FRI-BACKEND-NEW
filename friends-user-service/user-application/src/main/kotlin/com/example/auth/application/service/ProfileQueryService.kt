package com.example.auth.application.service

import com.example.auth.application.dto.ProfileDto
import com.example.auth.application.mapper.ProfileMapper
import com.example.user.domain.exception.ProfileNotFoundByMemberIdException
import com.example.user.domain.repository.ProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class ProfileQueryService(
    private val profileRepository: ProfileRepository
) {
    fun getProfile(memberId: UUID): ProfileDto {
        val profile =  profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        return ProfileMapper.profileToProfileDto(profile)
    }
}