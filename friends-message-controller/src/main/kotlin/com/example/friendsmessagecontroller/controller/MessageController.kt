package com.example.friendsmessagecontroller.controller

import com.example.friendsmessagecontroller.controller.dto.EnterChatRoomRequestDto
import com.example.friendsmessagecontroller.service.ChatRoomEnterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal")
class MessageController(
    private val chatRoomEnterService: ChatRoomEnterService
) {
    @PostMapping("/enter-chat")
    fun enterChatRoom(@RequestBody enterChatRoomRequestDto: EnterChatRoomRequestDto) {
        chatRoomEnterService.enterChatRoom(enterChatRoomRequestDto)
    }
}