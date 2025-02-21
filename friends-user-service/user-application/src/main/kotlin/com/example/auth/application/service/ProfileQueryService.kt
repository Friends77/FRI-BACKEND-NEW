package com.example.auth.application.service

import com.example.auth.application.dto.ProfileDto
import com.example.auth.application.mapper.ProfileMapper
import com.example.user.domain.dao.ProfileViewDao
import com.example.user.domain.exception.ProfileNotFoundByMemberIdException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class ProfileQueryService(
    private val profileViewDao: ProfileViewDao
) {
    fun getProfile(memberId: UUID): ProfileDto {
        val profileView =  profileViewDao.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        return ProfileMapper.profileViewToProfileDto(profileView)
    }
}