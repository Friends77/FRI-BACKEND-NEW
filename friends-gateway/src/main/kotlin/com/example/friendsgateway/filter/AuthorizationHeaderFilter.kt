package com.example.friendsgateway.filter

import com.example.friendsgateway.util.JwtUtil
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthorizationHeaderFilter(
    private val jwtUtil: JwtUtil
)  {


}