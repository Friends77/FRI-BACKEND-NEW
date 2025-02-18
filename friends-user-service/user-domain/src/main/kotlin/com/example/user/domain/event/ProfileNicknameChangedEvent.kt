package com.example.user.domain.event

import java.util.UUID

data class ProfileNicknameChangedEvent(
    val memberId : UUID,
    val nickname : String,
)