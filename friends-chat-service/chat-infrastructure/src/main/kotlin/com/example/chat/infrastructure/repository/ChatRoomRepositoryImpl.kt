package com.example.chat.infrastructure.repository

import com.example.chat.domain.entity.ChatRoom
import com.example.chat.domain.repository.ChatRoomRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRoomRepositoryImpl : JpaRepository<ChatRoom, UUID>, ChatRoomRepository {
}