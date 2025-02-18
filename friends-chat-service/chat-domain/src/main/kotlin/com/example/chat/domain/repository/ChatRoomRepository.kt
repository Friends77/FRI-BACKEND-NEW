package com.example.chat.domain.repository

import com.example.chat.domain.entity.ChatRoom
import java.util.Optional
import java.util.UUID

interface ChatRoomRepository {
    fun save(chatRoom: ChatRoom): ChatRoom
    fun findById(chatRoomId: UUID): Optional<ChatRoom>
}