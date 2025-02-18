package com.example.chat.domain.repository

import com.example.chat.domain.entity.ChatRoomMember
import java.util.UUID

interface ChatRoomMemberRepository {
    fun findAllByMemberIdHavingNotCustomProfile(memberId: UUID): List<ChatRoomMember>

    fun findAllIdByMemberId(memberId: UUID): List<UUID>
}