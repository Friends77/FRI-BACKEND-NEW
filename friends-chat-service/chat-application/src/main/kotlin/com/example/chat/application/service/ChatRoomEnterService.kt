package com.example.chat.application.service

import com.example.chat.application.client.UserServiceClient
import com.example.chat.application.dto.EnterChatRoomDto
import com.example.chat.domain.entity.ChatRoomMember
import com.example.chat.domain.exception.ChatRoomNotFoundException
import com.example.chat.domain.repository.ChatRoomRepository
import com.example.chat.domain.valueobject.Profile
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ChatRoomEnterService(
    private val chatRoomRepository: ChatRoomRepository,
    private val userServiceClient: UserServiceClient
) {
    fun enterChatRoom(enterChatRoomDto: EnterChatRoomDto) {
        val memberId = enterChatRoomDto.memberId
        val chatRoomId = enterChatRoomDto.chatRoomId
        val profileDto = userServiceClient.getProfile(memberId)
        val profile = Profile(
            profileDto.nickname,
            profileDto.profileImageUrl
        )
        val chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow {
            throw ChatRoomNotFoundException()
        }

        chatRoom.addChatRoomMember(ChatRoomMember(chatRoom, memberId, profile))
        // TODO : 들어간 채팅방에 온라인 유저로 연결하고 입장 메세지 전송
    }
}