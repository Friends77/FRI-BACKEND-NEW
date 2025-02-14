package com.example.friendsgateway.filter

import com.example.friendsgateway.util.JwtUtil
import org.springframework.stereotype.Component

@Component
class AdminAuthorizationFilter(
    jwtUtil: JwtUtil
) : AuthorizationHeaderFilter(jwtUtil = jwtUtil, authorities = listOf(Authority.ROLE_ADMIN))