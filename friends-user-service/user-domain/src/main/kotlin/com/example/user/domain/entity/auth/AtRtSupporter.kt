package com.example.user.domain.entity.auth

import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.AuthorityRole
import org.springframework.stereotype.Service
import java.util.UUID

@Service
interface AtRtSupporter{

    fun createAtRt(
        memberId: UUID,
        authorities : Collection<AuthorityRole>
    ): AtRt

    fun getMemberId(refreshToken: String): UUID

    fun getAuthorities(accessToken: String): Collection<AuthorityRole>
}