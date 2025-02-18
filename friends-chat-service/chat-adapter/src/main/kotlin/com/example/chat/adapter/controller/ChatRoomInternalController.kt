package com.example.chat.adapter.controller

import com.example.chat.application.usecase.ChatRoomMemberQueryUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/internal/")
class ChatRoomInternalController(
    private val chatRoomMemberQueryUseCase: ChatRoomMemberQueryUseCase
) {
    @GetMapping("/{memberId}/chat-room-ids")
    fun getJoinedChatRoomIds(
        @PathVariable(value = "memberId") memberIdStr: String
    ) : ResponseEntity<List<String>>{
        val memberId = UUID.fromString(memberIdStr)
        val result = chatRoomMemberQueryUseCase.getJoinedChatRoomIds(memberId)
        return ResponseEntity.ok(result.map { it.toString() })
    }
}