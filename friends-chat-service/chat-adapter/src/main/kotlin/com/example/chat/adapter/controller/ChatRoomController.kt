package com.example.chat.adapter.controller

import com.example.chat.adapter.dto.CreateChatRoomRequestDto
import com.example.chat.application.dto.CreateChatRoomDto
import com.example.chat.application.usecase.ChatRoomCreateUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/user/chat-room")
class ChatRoomController(
    private val chatRoomCreateUseCase: ChatRoomCreateUseCase
) {
    @PostMapping
    fun createChatRoom(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody createChatRoomRequestDto: CreateChatRoomRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val createChatRoomDto = CreateChatRoomDto(
            createChatRoomRequestDto.title,
            createChatRoomRequestDto.imageUrl,
            createChatRoomRequestDto.categories,
            memberId
        )
        chatRoomCreateUseCase.createChatRoom(createChatRoomDto)
        return ResponseEntity.status(HttpStatus.CREATED).body("채팅방 생성")
    }
}