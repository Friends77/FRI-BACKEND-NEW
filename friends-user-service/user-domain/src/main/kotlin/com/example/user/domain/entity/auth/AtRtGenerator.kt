package com.example.user.domain.entity.auth

import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.AuthorityRole
import java.util.UUID

interface AtRtGenerator{
    fun createAtRt(
        memberId: UUID,
        authorities : Collection<AuthorityRole>
    ): AtRt
}