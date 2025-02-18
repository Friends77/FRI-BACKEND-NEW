package com.example.user.domain.event

interface EventPublisher {
    fun publishProfileNicknameChangedEvent(event: ProfileNicknameChangedEvent)
    fun publishProfileImageUrlChangedEvent(event: ProfileImageUrlChangedEvent)
}