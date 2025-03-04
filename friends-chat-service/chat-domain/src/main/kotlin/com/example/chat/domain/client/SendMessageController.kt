package com.example.chat.domain.client

import com.example.chat.domain.client.request.EnterChatRoomRequestDto
import org.springframework.web.bind.annotation.RequestBody

interface SendMessageController {
    fun enterChatRoom(
        @RequestBody enterChatRoomRequestDto: EnterChatRoomRequestDto,
    )
}
