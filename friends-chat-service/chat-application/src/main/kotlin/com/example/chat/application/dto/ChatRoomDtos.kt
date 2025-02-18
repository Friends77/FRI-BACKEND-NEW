package com.example.chat.application.dto

import java.util.UUID

data class CreateChatRoomDto(
    val title: String,
    val imageUrl: String?,
    val categories: List<String>,
    val managerId: UUID,
)

data class EnterChatRoomDto(
    val chatRoomId: UUID,
    val memberId: UUID,
)