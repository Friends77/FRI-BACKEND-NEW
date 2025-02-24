package com.example.chat.application.service

import com.example.chat.application.client.MessageControllerClient
import com.example.chat.application.client.UserServiceClient
import com.example.chat.application.client.request.EnterChatRoomRequestDto
import com.example.chat.application.dto.CreateChatRoomDto
import com.example.chat.domain.entity.ChatRoom
import com.example.chat.domain.entity.ChatRoomMember
import com.example.chat.domain.exception.IllegalChatRoomPropertyException
import com.example.chat.domain.repository.ChatRoomRepository
import com.example.chat.domain.valueobject.Category
import com.example.chat.domain.valueobject.Profile
import com.example.chat.domain.valueobject.Title
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ChatRoomCreateService (
    private val chatRoomRepository: ChatRoomRepository,
    private val userServiceClient: UserServiceClient,
    private val messageControllerClient: MessageControllerClient
) {
    fun createChatRoom(createChatRoomDto: CreateChatRoomDto) {
        val managerId = createChatRoomDto.managerId
        val profileDto = userServiceClient.getProfile(managerId)
        val profile = Profile(
            profileDto.nickname,
            profileDto.profileImageUrl
        )

        val categoryList = createChatRoomDto.categories
        val title = createChatRoomDto.title
        val imageUrl = createChatRoomDto.imageUrl

        if (categoryList.isEmpty()) throw IllegalChatRoomPropertyException("최소 1개 이상의 카테고리가 필요합니다.")
        if (categoryList.size > 5) throw IllegalChatRoomPropertyException("최대 5개까지의 카테고리만 가능합니다.")
        val categories = categoryList.map { Category(it) }

        val chatRoom = ChatRoom(managerId, Title(title), categories)
        chatRoom.changeProfileImageUrl(imageUrl)
        chatRoom.addChatRoomMember(ChatRoomMember(chatRoom, managerId, profile))
        chatRoomRepository.save(chatRoom)

        messageControllerClient.enterChatRoom(
            EnterChatRoomRequestDto(
                chatRoomId = chatRoom.id.toString(),
                memberId = managerId.toString(),
                nickname = profile.nickname
            )
        )
    }
}