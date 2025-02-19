package com.example.chat.application.client

import com.example.chat.application.client.response.ProfileDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "friends-user-service")
interface UserServiceClient {
    // TODO : api 호출 시 발생하는 예외 처리
    @GetMapping("/api/user/profile/{memberId}")
    fun getProfile(@PathVariable memberId: UUID): ProfileDto
}