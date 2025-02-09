package com.example.friendservernew.auth.service.atrt

import com.atelier.server.jwt.JwtType
import com.atelier.server.jwt.JwtUtil
import com.example.friendservernew.auth.service.AtRtDto
import com.example.friendservernew.auth.service.UserTokenDto
import com.example.friendservernew.auth.exception.MissingJwtPayloadException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class AtRtService(
    private val jwtUtil: JwtUtil,
    @Value("\${jwt.access-token-expiration}") private val accessTokenExpiration: Long,
    @Value("\${jwt.refresh-token-expiration}") private val refreshTokenExpiration: Long,
) {
    companion object {
        const val MEMBER_ID = "memberId"
        const val AUTHORITIES = "authorities"
    }

    fun createAtRt(userTokenDto: UserTokenDto): AtRtDto {
        val memberId = userTokenDto.memberId
        val authorities = userTokenDto.authorities
        val accessToken = createAccessToken(memberId, authorities)
        val refreshToken = createRefreshToken(memberId, authorities)
        return AtRtDto(accessToken = accessToken, refreshToken = refreshToken)
    }

    fun getMemberId(token: String): Long =
        jwtUtil.getClaim(token, MEMBER_ID, Long::class.javaObjectType) ?: throw MissingJwtPayloadException(MEMBER_ID)

    fun getAuthorities(token: String): List<GrantedAuthority> {
        val authorities: List<String> =
            jwtUtil.getClaim(token, AUTHORITIES, List::class.javaObjectType)?.filterIsInstance<String>()
                ?: throw MissingJwtPayloadException(AUTHORITIES)
        return authorities.map { SimpleGrantedAuthority(it) }
    }

    fun isValidRefreshToken(token: String): Boolean = jwtUtil.isValid(token) && getType(token) == JwtType.REFRESH

    fun isValidAccessToken(token: String): Boolean = jwtUtil.isValid(token) && getType(token) == JwtType.ACCESS

    fun getType(token: String): JwtType {
        val type = jwtUtil.getClaim(token, "type", String::class.java) ?: throw MissingJwtPayloadException("type")
        return JwtType.valueOf(type)
    }

    fun createAuthentication(token: String): Authentication {
        val memberId = getMemberId(token)
        val authorities = getAuthorities(token)
        return UsernamePasswordAuthenticationToken(memberId, token, authorities)
    }

    private fun createAccessToken(
        memberId: Long,
        authorities: Collection<GrantedAuthority>,
    ): String =
        jwtUtil.createToken(
            "memberId" to memberId,
            "authorities" to authorities.map { it.authority },
            "type" to JwtType.ACCESS,
            expirationSeconds = accessTokenExpiration,
        )

    private fun createRefreshToken(
        memberId: Long,
        authorities: Collection<GrantedAuthority>,
    ): String =
        jwtUtil.createToken(
            "memberId" to memberId,
            "authorities" to authorities.map { it.authority },
            "type" to JwtType.REFRESH,
            expirationSeconds = refreshTokenExpiration,
        )
}