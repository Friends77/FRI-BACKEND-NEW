package com.example.user.domain.auth

import com.example.user.domain.valueobject.type.AuthorityRoleType
import com.example.user.domain.valueobject.type.JwtType
import java.util.UUID

interface JwtClaimReader {
    fun getMemberId(token : String) : UUID?
    fun getEmail(token : String) : String?
    fun getAuthorities(token : String) : List<AuthorityRoleType>?
    fun getType(token : String) : JwtType?
}