package com.example.user.domain.auth

import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.type.AuthorityRoleType
import java.util.UUID

interface AtRtGenerator{
    fun createAtRt(
        memberId: UUID,
        authorities : Collection<AuthorityRoleType>
    ): AtRt
}