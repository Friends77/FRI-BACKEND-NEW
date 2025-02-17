package com.example.user.domain.service

import com.example.user.domain.entity.Profile
import com.example.user.domain.exception.ProfileNotFoundByMemberIdException
import com.example.user.domain.repository.ProfileRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProfileQueryService(
    private val profileRepository: ProfileRepository
) {
    fun getProfile(memberId: UUID): Profile {
        return profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
    }
}