package com.example.chat.application.usecase

import com.example.chat.application.client.UserServiceClient
import com.example.chat.application.dto.EnterChatRoomDto
import com.example.chat.domain.service.ChatRoomEnterService
import com.example.chat.domain.valueobject.Profile
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ChatRoomEnterUseCase(
    private val chatRoomEnterService: ChatRoomEnterService,
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
        chatRoomEnterService.enterChatRoom(
            memberId,
            chatRoomId,
            profile
        )
    }
}