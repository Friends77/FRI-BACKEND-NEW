package com.example.chat.domain.event

interface EventHandler {
    fun handleProfileNicknameChangedEvent(event: ProfileNicknameChangedEvent)
    fun handleProfileImageUrlChangedEvent(event: ProfileImageUrlChangedEvent)
}