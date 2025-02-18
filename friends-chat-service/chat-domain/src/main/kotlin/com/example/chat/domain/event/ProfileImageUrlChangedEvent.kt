package com.example.chat.domain.event

import java.util.UUID

data class ProfileImageUrlChangedEvent(
    val memberId : UUID,
    val profileImageUrl : String,
)