package com.example.user.infrastructure.auth

import com.example.user.domain.auth.AtRtGenerator
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.type.AuthorityRoleType
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.type.JwtType
import com.example.user.infrastructure.util.JwtUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AtRtGeneratorImpl(
    private val jwtUtil: JwtUtil,
    @Value("\${jwt.access-token-expiration}") private val accessTokenExpiration: Long,
    @Value("\${jwt.refresh-token-expiration}") private val refreshTokenExpiration: Long,
) : AtRtGenerator {
    override fun createAtRt(
        memberId: UUID,
        authorities : Collection<AuthorityRoleType>
    ): AtRt {
        val accessToken = createAccessToken(memberId, authorities)
        val refreshToken = createRefreshToken(memberId, authorities)
        return AtRt(accessToken = accessToken, refreshToken = refreshToken)
    }

    private fun createAccessToken(
        memberId: UUID,
        authorities: Collection<AuthorityRoleType>,
    ): String =
        jwtUtil.createToken(
            JwtKey.MEMBER_ID.value to memberId,
            JwtKey.AUTHORITIES.value to authorities.map { it.name },
            JwtKey.TYPE.value to JwtType.ACCESS,
            expirationSeconds = accessTokenExpiration,
        )

    private fun createRefreshToken(
        memberId: UUID,
        authorities: Collection<AuthorityRoleType>,
    ): String =
        jwtUtil.createToken(
            JwtKey.MEMBER_ID.value to memberId,
            JwtKey.AUTHORITIES.value to authorities.map { it.name },
            JwtKey.TYPE.value to JwtType.REFRESH,
            expirationSeconds = refreshTokenExpiration,
        )
}