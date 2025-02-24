package com.example.chat.domain.repository

import com.example.chat.domain.entity.ChatRoomCategory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRoomCategoryRepository : JpaRepository<ChatRoomCategory, UUID> {
}