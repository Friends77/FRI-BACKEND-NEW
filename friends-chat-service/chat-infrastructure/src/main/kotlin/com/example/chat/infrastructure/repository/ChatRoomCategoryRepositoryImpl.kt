package com.example.chat.infrastructure.repository

import com.example.chat.domain.entity.ChatRoomCategory
import com.example.chat.domain.repository.ChatRoomCategoryRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRoomCategoryRepositoryImpl : JpaRepository<ChatRoomCategory, UUID>, ChatRoomCategoryRepository {
}