package com.example.chat.infrastructure.client

import com.example.chat.domain.client.SendMessageController
import com.example.chat.domain.client.request.EnterChatRoomRequestDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "friends-message-controller")
interface SendMessageControllerClient {
    @PostMapping("/internal/enter-chat")
    fun enterChatRoom(
        @RequestBody enterChatRoomRequestDto: EnterChatRoomRequestDto,
    )
}

@Component
class SendMessageControllerImpl(
    private val sendMessageControllerClient: SendMessageControllerClient,
) : SendMessageController {
    override fun enterChatRoom(enterChatRoomRequestDto: EnterChatRoomRequestDto) {
        sendMessageControllerClient.enterChatRoom(enterChatRoomRequestDto)
    }
}
