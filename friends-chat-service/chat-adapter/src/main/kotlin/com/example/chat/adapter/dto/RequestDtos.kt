package com.example.chat.adapter.dto

data class CreateChatRoomRequestDto(
    val title: String,
    val imageUrl: String?,
    val categories: List<String>,
)