package com.example.message.domain.event

data class MessageReceiveEvent(
    val clientMessageId: String,
    val chatRoomId: String,
    val senderId: String,
    val content: String,
    val type: String
)