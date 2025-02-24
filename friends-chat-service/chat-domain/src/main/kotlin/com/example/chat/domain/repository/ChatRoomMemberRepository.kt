package com.example.chat.domain.repository

import com.example.chat.domain.entity.ChatRoomMember
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ChatRoomMemberRepository : JpaRepository<ChatRoomMember, UUID> {
    @Query("SELECT crm FROM ChatRoomMember crm WHERE crm.memberId = :memberId AND crm.isCustomProfile = false")
    fun findAllByMemberIdHavingNotCustomProfile(memberId: UUID): List<ChatRoomMember>

    @Query("SELECT c.id FROM ChatRoomMember c WHERE c.memberId = :memberId")
    fun findAllIdByMemberId(memberId: UUID): List<UUID>
}