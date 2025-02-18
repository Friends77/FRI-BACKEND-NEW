package com.example.chat.domain.event

interface EventConsumer {
    fun consumeProfileNicknameChangedEvent(payload: String)
    fun consumeProfileImageUrlChangedEvent(payload: String)
}