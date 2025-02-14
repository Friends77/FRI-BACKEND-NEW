package com.example.user.domain.service


import com.example.user.domain.util.JwtUtil
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.AuthorityRole
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
    companion object {
        const val MEMBER_ID = "memberId"
        const val AUTHORITIES = "authorities"
    }

    fun createAtRt(
        memberId: UUID,
        authorities : Collection<AuthorityRole>
    ): AtRt {
        val accessToken = createAccessToken(memberId, authorities)
        val refreshToken = createRefreshToken(memberId, authorities)
        return AtRt(accessToken = accessToken, refreshToken = refreshToken)
    }

    private fun createAccessToken(
        memberId: UUID,
        authorities: Collection<AuthorityRole>,
    ): String =
        jwtUtil.createToken(
            "memberId" to memberId,
            "authorities" to authorities.map { it.name },
            "type" to JwtType.ACCESS,
            expirationSeconds = accessTokenExpiration,
        )

    private fun createRefreshToken(
        memberId: UUID,
        authorities: Collection<AuthorityRole>,
    ): String =
        jwtUtil.createToken(
            "memberId" to memberId,
            "authorities" to authorities.map { it.name },
            "type" to JwtType.REFRESH,
            expirationSeconds = refreshTokenExpiration,
        )
}