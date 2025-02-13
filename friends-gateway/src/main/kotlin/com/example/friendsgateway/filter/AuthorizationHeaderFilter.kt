package com.example.friendsgateway.filter

import com.example.friendsgateway.util.JwtUtil
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID
import javax.naming.directory.InvalidAttributesException

@Component
class AuthorizationHeaderFilter(
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {
    companion object {
        const val MEMBER_ID = "memberId"
        const val AUTHORITIES = "authorities"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = resolveToken(request)
        if (token != null && isValidAccessToken(token)) {
            val authentication: Authentication = createAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun createAuthentication(token: String): Authentication {
        val memberId = getMemberId(token)
        val authorities = getAuthorities(token)
        return UsernamePasswordAuthenticationToken(memberId, token, authorities)
    }

    private fun getMemberId(token: String): UUID {
        val uuid = jwtUtil.getClaim(token, MEMBER_ID, String::class.java) ?: throw InvalidAttributesException("memberId is missing")
        return UUID.fromString(uuid)
    }

    private fun getAuthorities(token: String): List<GrantedAuthority> {
        val authorities: List<String> =
            jwtUtil.getClaim(token, AUTHORITIES, List::class.javaObjectType)?.filterIsInstance<String>()
                ?: throw InvalidAttributesException("authorities is missing")
        return authorities.map { SimpleGrantedAuthority(it) }
    }

    private fun getType(token: String): JwtType {
        val type = jwtUtil.getClaim(token, "type", String::class.java) ?: throw InvalidAttributesException("type is missing")
        return JwtType.valueOf(type)
    }

    private fun isValidAccessToken(token: String): Boolean = jwtUtil.isValid(token) && getType(token) == JwtType.ACCESS

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (!bearerToken.isNullOrEmpty() && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }

}