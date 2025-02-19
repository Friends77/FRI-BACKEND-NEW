package com.example.message.domain.event

data class MessageSendEvent(
    val clientMessageId : String,
    val messageId : String,
    val chatRoomId : String,
    val senderId : String,
    val content : String,
    val createdAt : String,
    val type : String
)