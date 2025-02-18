package com.example.chat.application.usecase

import com.example.chat.domain.service.ChatRoomMemberQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class ChatRoomMemberQueryUseCase(
    private val chatRoomMemberQueryService: ChatRoomMemberQueryService
) {
    fun getJoinedChatRoomIds(memberId: UUID): List<UUID> {
        return chatRoomMemberQueryService.getJoinedChatRoomIds(memberId)
    }
}