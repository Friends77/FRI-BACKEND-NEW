package com.example.friendsmessagecontroller.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "friends-chat-service")
interface ChatServiceClient {
    @GetMapping("/internal/{memberId}/chat-room-ids")
    fun getJoinedChatRoomIds(@PathVariable memberId: String): List<String>
}