package com.example.user.domain.dao

import com.example.user.domain.dao.data.ProfileView
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProfileViewDao : JpaRepository<ProfileView, UUID> {
    fun findByMemberId(memberId: UUID): ProfileView?
}