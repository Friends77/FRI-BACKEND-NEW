package com.example.user.domain.event

import java.util.UUID

data class ProfileImageUrlChangedEvent(
    val memberId : UUID,
    val profileImageUrl : String?,
)