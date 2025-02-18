package com.example.chat.application.handler

import com.example.chat.domain.event.EventHandler
import com.example.chat.domain.event.ProfileImageUrlChangedEvent
import com.example.chat.domain.event.ProfileNicknameChangedEvent
import com.example.chat.domain.service.ChatMemberProfileChangeService
import org.springframework.stereotype.Service

@Service
class EventHandlerImpl(
    private val chatRoomProfileChangeService: ChatMemberProfileChangeService
) : EventHandler {
    override fun handleProfileNicknameChangedEvent(event: ProfileNicknameChangedEvent) {
        val memberId = event.memberId
        val nickname = event.nickname
        chatRoomProfileChangeService.changeProfileNickname(memberId, nickname)
    }

    override fun handleProfileImageUrlChangedEvent(event: ProfileImageUrlChangedEvent) {
        val memberId = event.memberId
        val profileImageUrl = event.profileImageUrl
        chatRoomProfileChangeService.changeProfileImageUrl(memberId, profileImageUrl)
    }
}