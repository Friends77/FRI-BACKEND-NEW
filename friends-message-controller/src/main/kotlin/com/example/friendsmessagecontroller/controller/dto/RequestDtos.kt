package com.example.friendsmessagecontroller.controller.dto

data class EnterChatRoomRequestDto(
    val chatRoomId: String,
    val memberId: String,
    val nickname: String
)