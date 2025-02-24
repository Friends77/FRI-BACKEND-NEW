package com.example.chat.domain.repository

import com.example.chat.domain.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRoomRepository : JpaRepository<ChatRoom, UUID> {
}