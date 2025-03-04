package com.example.chat.infrastructure.client

import com.example.chat.domain.client.UserInformationService
import com.example.chat.domain.client.response.ProfileDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "friends-user-service")
interface UserInformationServiceClient {
    // TODO : api 호출 시 발생하는 예외 처리
    @GetMapping("/api/user/profile/{memberId}")
    fun getProfile(
        @PathVariable memberId: UUID,
    ): ProfileDto
}

@Component
class UserInformationServiceImpl(
    userInformationServiceClient: UserInformationServiceClient,
) : UserInformationService {
    private val memberProfileFeignClient: UserInformationServiceClient = userInformationServiceClient

    override fun getProfile(memberId: UUID): ProfileDto = memberProfileFeignClient.getProfile(memberId)
}
