package com.example.chat.application.service

import com.example.chat.application.dto.EnterChatRoomDto
import com.example.chat.domain.client.SendMessageController
import com.example.chat.domain.client.UserInformationService
import com.example.chat.domain.client.request.EnterChatRoomRequestDto
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
    private val userInformationService: UserInformationService,
    private val sendMessageController: SendMessageController,
) {
    fun enterChatRoom(enterChatRoomDto: EnterChatRoomDto) {
        val memberId = enterChatRoomDto.memberId
        val chatRoomId = enterChatRoomDto.chatRoomId
        val profileDto = userInformationService.getProfile(memberId)
        val profile =
            Profile(
                profileDto.nickname,
                profileDto.profileImageUrl,
            )
        val chatRoom =
            chatRoomRepository.findById(chatRoomId).orElseThrow {
                throw ChatRoomNotFoundException()
            }

        chatRoom.addChatRoomMember(ChatRoomMember(chatRoom, memberId, profile))

        // 채팅방 입장 시 해당 채팅방에 온라인 유저로 등록 및 시스템 입장 메세지 전송
        sendMessageController.enterChatRoom(
            EnterChatRoomRequestDto(
                chatRoomId = chatRoomId.toString(),
                memberId = memberId.toString(),
                nickname = profile.nickname,
            ),
        )
    }
}
