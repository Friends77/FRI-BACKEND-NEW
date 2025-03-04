package com.example.chat.domain.client

import com.example.chat.domain.client.response.ProfileDto
import java.util.UUID

interface UserInformationService {
    // TODO : api 호출 시 발생하는 예외 처리
    fun getProfile(memberId: UUID): ProfileDto
}
