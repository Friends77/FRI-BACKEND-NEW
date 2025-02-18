package com.example.chat.infrastructure.repository

import com.example.chat.domain.entity.ChatRoomMember
import com.example.chat.domain.repository.ChatRoomMemberRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ChatRoomMemberRepositoryImpl : JpaRepository<ChatRoomMember, UUID>, ChatRoomMemberRepository {
    @Query("SELECT crm FROM ChatRoomMember crm WHERE crm.memberId = :memberId AND crm.isCustomProfile = false")
    override fun findByMemberIdHavingNotCustomProfile(memberId: UUID): List<ChatRoomMember>
}