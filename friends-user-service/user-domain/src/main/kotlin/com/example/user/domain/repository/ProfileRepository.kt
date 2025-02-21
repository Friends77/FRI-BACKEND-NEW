package com.example.user.domain.repository

import com.example.user.domain.entity.Profile
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProfileRepository {
    fun save(profile: Profile): Profile
    fun findByMemberId(memberId: UUID): Profile?
}