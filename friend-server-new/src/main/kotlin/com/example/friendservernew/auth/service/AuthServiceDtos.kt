package com.example.friendservernew.auth.service

import org.springframework.security.core.GrantedAuthority
import java.util.UUID


data class UserTokenDto(
    val memberId: UUID,
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

data class SendEmailVerifyCodeDto(
    val email: String,
)

data class CreateEmailAuthTokenDto(
    val email: String,
    val code: String,
)