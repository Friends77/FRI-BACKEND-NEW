package com.example.friendsmessagecontroller.event

data class MessageReceiveEvent(
    val clientMessageId: String,
    val chatRoomId: String,
    val senderId: String,
    val content: String,
    val type: String
)