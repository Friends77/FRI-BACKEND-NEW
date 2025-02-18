package com.example.chat.domain.service

import com.example.chat.domain.repository.ChatRoomMemberRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatRoomMemberQueryService(
    private val chatRoomMemberRepository: ChatRoomMemberRepository
) {
    fun getJoinedChatRoomIds(memberId: UUID): List<UUID> {
        return chatRoomMemberRepository.findAllIdByMemberId(memberId)
    }
}