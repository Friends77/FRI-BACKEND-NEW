package com.example.chat.application.client

import com.example.chat.application.client.request.EnterChatRoomRequestDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "friends-message-controller")
interface MessageControllerClient {
    @PostMapping("/internal/enter-chat")
    fun enterChatRoom(@RequestBody enterChatRoomRequestDto: EnterChatRoomRequestDto)
}