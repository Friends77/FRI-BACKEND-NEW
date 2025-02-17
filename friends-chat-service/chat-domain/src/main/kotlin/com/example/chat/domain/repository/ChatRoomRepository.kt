package com.example.chat.domain.repository

import com.example.chat.domain.entity.ChatRoom

interface ChatRoomRepository {
    fun save(chatRoom: ChatRoom) : ChatRoom
}