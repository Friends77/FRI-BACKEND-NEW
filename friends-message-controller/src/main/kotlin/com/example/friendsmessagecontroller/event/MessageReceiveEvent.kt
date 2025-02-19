package com.example.friendsmessagecontroller.event

data class MessageReceiveEvent(
    val clientMessageId: String,
    val chatRoomId: String,
    val content: String,
    val type: String
)