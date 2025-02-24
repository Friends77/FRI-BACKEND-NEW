package com.example.chat.application.client.request

data class EnterChatRoomRequestDto(
    val chatRoomId: String,
    val memberId: String,
    val nickname: String
)