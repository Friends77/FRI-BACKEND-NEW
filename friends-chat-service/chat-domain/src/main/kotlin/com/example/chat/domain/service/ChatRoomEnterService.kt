package com.example.chat.domain.service

import com.example.chat.domain.entity.ChatRoomMember
import com.example.chat.domain.exception.ChatRoomNotFoundException
import com.example.chat.domain.repository.ChatRoomRepository
import com.example.chat.domain.valueobject.Profile
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatRoomEnterService (
    private val chatRoomRepository: ChatRoomRepository
){
    fun enterChatRoom(
        memberId : UUID,
        chatRoomId : UUID,
        profile: Profile
    ) {
        val chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow {
            throw ChatRoomNotFoundException()
        }

        chatRoom.addChatRoomMember(ChatRoomMember(chatRoom, memberId, profile))
    }
}