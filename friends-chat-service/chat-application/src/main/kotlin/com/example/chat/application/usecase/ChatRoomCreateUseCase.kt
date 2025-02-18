package com.example.chat.application.usecase

import com.example.chat.application.client.UserServiceClient
import com.example.chat.application.dto.CreateChatRoomDto
import com.example.chat.domain.service.ChatRoomCreateService
import com.example.chat.domain.valueobject.Profile
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ChatRoomCreateUseCase (
    private val chatRoomCreateService: ChatRoomCreateService,
    private val userServiceClient: UserServiceClient
) {
    fun createChatRoom(createChatRoomDto: CreateChatRoomDto) {
        val managerId = createChatRoomDto.managerId
        val profileDto = userServiceClient.getProfile(managerId)
        val profile = Profile(
            profileDto.nickname,
            profileDto.profileImageUrl
        )
        chatRoomCreateService.createChatRoom(
            createChatRoomDto.title,
            createChatRoomDto.imageUrl,
            createChatRoomDto.categories,
            managerId,
            profile
        )
    }
}