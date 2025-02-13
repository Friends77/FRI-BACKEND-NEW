package com.auth.application

import org.springframework.security.core.GrantedAuthority
import java.util.UUID

data class CreateAtRtDto(
    val memberId : UUID,
    val authorities : Collection<GrantedAuthority>
)

data class AtRtDto(
    val accessToken : String,
    val refreshToken : String
)