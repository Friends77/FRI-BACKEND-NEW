package com.example.chat.application.service

import com.example.chat.domain.repository.ChatRoomMemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class ChatRoomMemberQueryService(
    private val chatRoomMemberRepository: ChatRoomMemberRepository,
) {
    fun getJoinedChatRoomIds(memberId: UUID): List<UUID> = chatRoomMemberRepository.findAllChatRoomIdByMemberId(memberId)
}
