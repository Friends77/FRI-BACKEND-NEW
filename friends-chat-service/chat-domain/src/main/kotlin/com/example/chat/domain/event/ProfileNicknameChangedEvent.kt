package com.example.chat.domain.event

import java.util.UUID

data class ProfileNicknameChangedEvent(
    val memberId : UUID,
    val nickname : String,
)