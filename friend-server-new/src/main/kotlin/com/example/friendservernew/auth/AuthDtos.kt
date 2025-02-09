package com.example.friendservernew.auth

import org.springframework.security.core.GrantedAuthority


data class UserTokenDto(
    val memberId: Long,
    val authorities: Collection<GrantedAuthority>,
)

data class AtRtDto(
    val accessToken: String,
    val refreshToken: String,
)

data class ValidateEmailAuthTokenDto(
    val token: String,
    val email: String,
)