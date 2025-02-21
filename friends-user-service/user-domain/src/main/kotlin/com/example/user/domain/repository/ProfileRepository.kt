package com.example.user.domain.repository

import com.example.user.domain.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProfileRepository : JpaRepository<Profile, UUID> {
    fun findByMemberId(memberId: UUID): Profile?
}