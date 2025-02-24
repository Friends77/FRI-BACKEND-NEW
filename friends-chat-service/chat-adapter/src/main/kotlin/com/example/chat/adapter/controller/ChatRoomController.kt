package com.example.chat.adapter.controller

import com.example.chat.adapter.dto.CreateChatRoomRequestDto
import com.example.chat.application.dto.CreateChatRoomDto
import com.example.chat.application.dto.EnterChatRoomDto
import com.example.chat.application.service.ChatRoomCreateService
import com.example.chat.application.service.ChatRoomEnterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/user/chat-room")
class ChatRoomController(
    private val chatRoomCreateService: ChatRoomCreateService,
    private val chatRoomEnterService: ChatRoomEnterService
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
        chatRoomCreateService.createChatRoom(createChatRoomDto)
        return ResponseEntity.status(HttpStatus.CREATED).body("채팅방 생성")
    }

    @PostMapping("/enter/{chatRoomId}")
    fun enterChatRoom(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @PathVariable chatRoomId: UUID,
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val enterChatRoomDto = EnterChatRoomDto(
            memberId,
            chatRoomId
        )
        chatRoomEnterService.enterChatRoom(enterChatRoomDto)
        return ResponseEntity.ok("채팅방 입장")
    }
}