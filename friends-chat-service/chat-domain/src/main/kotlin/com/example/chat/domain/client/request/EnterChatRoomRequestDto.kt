package com.example.chat.domain.client.request

data class EnterChatRoomRequestDto(
    val chatRoomId: String,
    val memberId: String,
    val nickname: String,
)
