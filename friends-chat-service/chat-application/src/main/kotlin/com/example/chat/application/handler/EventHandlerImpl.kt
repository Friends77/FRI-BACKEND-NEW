package com.example.chat.application.handler

import com.example.chat.domain.event.EventHandler
import com.example.chat.domain.event.ProfileImageUrlChangedEvent
import com.example.chat.domain.event.ProfileNicknameChangedEvent
import com.example.chat.domain.repository.ChatRoomMemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EventHandlerImpl(
    private val chatRoomMemberRepository: ChatRoomMemberRepository
) : EventHandler {
    override fun handleProfileNicknameChangedEvent(event: ProfileNicknameChangedEvent) {
        val memberId = event.memberId
        val nickname = event.nickname

        val chatRoomMembers = chatRoomMemberRepository.findAllByMemberIdHavingNotCustomProfile(memberId)
        chatRoomMembers.forEach { it.changeProfileNickname(nickname) }
    }

    override fun handleProfileImageUrlChangedEvent(event: ProfileImageUrlChangedEvent) {
        val memberId = event.memberId
        val profileImageUrl = event.profileImageUrl
        val chatRoomMembers = chatRoomMemberRepository.findAllByMemberIdHavingNotCustomProfile(memberId)
        chatRoomMembers.forEach { it.changeProfileImageUrl(profileImageUrl) }
    }
}