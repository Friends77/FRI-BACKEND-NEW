package com.example.chat.domain.repository

import com.example.chat.domain.entity.ChatRoomMember
import java.util.UUID

interface ChatRoomMemberRepository {
    fun findByMemberIdHavingNotCustomProfile(memberId: UUID): List<ChatRoomMember>
}