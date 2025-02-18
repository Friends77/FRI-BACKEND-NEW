package com.example.chat.application.client.response

import java.util.UUID

data class ProfileDto(
    val memberId : UUID,
    val nickname : String,
    val profileImageUrl : String?,
)