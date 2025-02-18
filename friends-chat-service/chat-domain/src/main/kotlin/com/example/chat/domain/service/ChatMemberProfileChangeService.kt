package com.example.chat.domain.service

import com.example.chat.domain.repository.ChatRoomMemberRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatMemberProfileChangeService (
    private val chatRoomMemberRepository: ChatRoomMemberRepository
){
    fun changeProfileNickname(memberId: UUID, newNickname: String) {
        val chatRoomMembers = chatRoomMemberRepository.findByMemberIdHavingNotCustomProfile(memberId)
        chatRoomMembers.forEach { it.changeProfileNickname(newNickname) }
    }

    fun changeProfileImageUrl(memberId: UUID, newImageUrl: String) {
        val chatRoomMembers = chatRoomMemberRepository.findByMemberIdHavingNotCustomProfile(memberId)
        chatRoomMembers.forEach { it.changeProfileImageUrl(newImageUrl) }
    }
}