package com.example.chat.infrastructure.repository

import com.example.chat.domain.entity.ChatRoomMember
import com.example.chat.domain.repository.ChatRoomMemberRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ChatRoomMemberRepositoryImpl : JpaRepository<ChatRoomMember, UUID>, ChatRoomMemberRepository {
    @Query("SELECT crm FROM ChatRoomMember crm WHERE crm.memberId = :memberId AND crm.isCustomProfile = false")
    override fun findAllByMemberIdHavingNotCustomProfile(memberId: UUID): List<ChatRoomMember>

    @Query("SELECT c.id FROM ChatRoomMember c WHERE c.memberId = :memberId")
    override fun findAllIdByMemberId(memberId: UUID): List<UUID>
}