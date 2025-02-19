package com.example.friendsmessagecontroller.controller.dto

data class MessageReceiveDto (
    val clientMessageId: String,
    val chatRoomId: String,
    val content: String,
    val type: String
)