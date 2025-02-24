package com.example.friendsmessagecontroller.service

import com.example.friendsmessagecontroller.controller.dto.EnterChatRoomRequestDto
import com.example.friendsmessagecontroller.event.KafkaEventPublisher
import com.example.friendsmessagecontroller.event.MessageReceiveEvent
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatRoomEnterService(
    private val chatConnectService: ChatConnectService,
    private val kafkaEventPublisher: KafkaEventPublisher,
) {
    fun enterChatRoom(enterChatRoomRequestDto: EnterChatRoomRequestDto) {
        val memberId = enterChatRoomRequestDto.memberId
        val chatRoomId = enterChatRoomRequestDto.chatRoomId
        val nickname = enterChatRoomRequestDto.nickname

        chatConnectService.connectOneChat(memberId = memberId, chatRoomId = chatRoomId)
        kafkaEventPublisher.publishMessageReceiveEvent(
            MessageReceiveEvent(
                clientMessageId = UUID.randomUUID().toString(),
                chatRoomId = chatRoomId,
                senderId = memberId,
                content = "$nickname 님이 입장하셨습니다.",
                type = "SYSTEM_MEMBER_ENTER"
            )
        )
    }
}