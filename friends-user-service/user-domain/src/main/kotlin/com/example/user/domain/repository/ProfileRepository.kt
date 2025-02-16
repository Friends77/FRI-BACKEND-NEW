package com.example.user.domain.repository

import com.example.user.domain.entity.Profile
import java.util.UUID

interface ProfileRepository {
    fun save(profile: Profile): Profile
    fun findByMemberId(memberId: UUID): Profile?
}