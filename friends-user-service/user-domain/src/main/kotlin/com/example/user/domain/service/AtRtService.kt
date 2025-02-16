package com.example.user.domain.service


import com.example.user.domain.exception.InvalidRefreshTokenException
import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.util.JwtUtil
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.AuthorityRole
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.JwtType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AtRtService(
    private val jwtUtil: JwtUtil,
    @Value("\${jwt.access-token-expiration}") private val accessTokenExpiration: Long,
    @Value("\${jwt.refresh-token-expiration}") private val refreshTokenExpiration: Long,
) {

    fun createAtRt(
        memberId: UUID,
        authorities : Collection<AuthorityRole>
    ): AtRt {
        val accessToken = createAccessToken(memberId, authorities)
        val refreshToken = createRefreshToken(memberId, authorities)
        return AtRt(accessToken = accessToken, refreshToken = refreshToken)
    }

    fun getMemberId(refreshToken: String): UUID {
        val memberIdStr = jwtUtil.getClaim(refreshToken, JwtKey.MEMBER_ID.value, String::class.java)
            ?: throw MissingJwtPayloadException(JwtKey.MEMBER_ID.value)
        return try {
            UUID.fromString(memberIdStr)
        } catch (e: Exception) {
            throw MissingJwtPayloadException(JwtKey.MEMBER_ID.value)
        }
    }

    fun getAuthorities(accessToken: String): Collection<AuthorityRole> {
        val authorities = jwtUtil.getClaim(accessToken, JwtKey.AUTHORITIES.value, List::class.javaObjectType)?.filterIsInstance<String>()
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
        jwtUtil.createToken(
            JwtKey.MEMBER_ID.value to memberId,
            JwtKey.AUTHORITIES.value to authorities.map { it.name },
            JwtKey.TYPE.value to JwtType.ACCESS,
            expirationSeconds = accessTokenExpiration,
        )

    private fun createRefreshToken(
        memberId: UUID,
        authorities: Collection<AuthorityRole>,
    ): String =
        jwtUtil.createToken(
            JwtKey.MEMBER_ID.value to memberId,
            JwtKey.AUTHORITIES.value to authorities.map { it.name },
            JwtKey.TYPE.value to JwtType.REFRESH,
            expirationSeconds = refreshTokenExpiration,
        )
}