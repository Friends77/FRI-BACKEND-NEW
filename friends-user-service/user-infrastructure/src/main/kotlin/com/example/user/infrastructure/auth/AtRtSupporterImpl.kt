package com.example.user.infrastructure.auth

import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.entity.auth.AtRtSupporter
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.AuthorityRole
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.JwtType
import com.example.user.infrastructure.util.JwtUtilImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AtRtSupporterImpl(
    private val jwtUtilImpl: JwtUtilImpl,
    @Value("\${jwt.access-token-expiration}") private val accessTokenExpiration: Long,
    @Value("\${jwt.refresh-token-expiration}") private val refreshTokenExpiration: Long,
) : AtRtSupporter {
    override fun createAtRt(
        memberId: UUID,
        authorities : Collection<AuthorityRole>
    ): AtRt {
        val accessToken = createAccessToken(memberId, authorities)
        val refreshToken = createRefreshToken(memberId, authorities)
        return AtRt(accessToken = accessToken, refreshToken = refreshToken)
    }

    override fun getMemberId(token: String): UUID {
        val memberIdStr = jwtUtilImpl.getClaim(token, JwtKey.MEMBER_ID.value, String::class.java)
            ?: throw MissingJwtPayloadException(JwtKey.MEMBER_ID.value)
        return try {
            UUID.fromString(memberIdStr)
        } catch (e: Exception) {
            throw MissingJwtPayloadException(JwtKey.MEMBER_ID.value)
        }
    }

    override fun getAuthorities(token: String): Collection<AuthorityRole> {
        val authorities = jwtUtilImpl.getClaim(token, JwtKey.AUTHORITIES.value, List::class.javaObjectType)?.filterIsInstance<String>()
            ?: throw MissingJwtPayloadException(JwtKey.AUTHORITIES.value)
        return try {
            authorities.map { AuthorityRole.valueOf(it) }
        } catch (e: Exception) {
            throw MissingJwtPayloadException(JwtKey.AUTHORITIES.value)
        }
    }

    private fun createAccessToken(
        memberId: UUID,
        authorities: Collection<AuthorityRole>,
    ): String =
        jwtUtilImpl.createToken(
            JwtKey.MEMBER_ID.value to memberId,
            JwtKey.AUTHORITIES.value to authorities.map { it.name },
            JwtKey.TYPE.value to JwtType.ACCESS,
            expirationSeconds = accessTokenExpiration,
        )

    private fun createRefreshToken(
        memberId: UUID,
        authorities: Collection<AuthorityRole>,
    ): String =
        jwtUtilImpl.createToken(
            JwtKey.MEMBER_ID.value to memberId,
            JwtKey.AUTHORITIES.value to authorities.map { it.name },
            JwtKey.TYPE.value to JwtType.REFRESH,
            expirationSeconds = refreshTokenExpiration,
        )
}