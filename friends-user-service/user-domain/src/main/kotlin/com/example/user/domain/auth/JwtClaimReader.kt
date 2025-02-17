package com.example.user.domain.auth

import com.example.user.domain.valueobject.AuthorityRole
import com.example.user.domain.valueobject.JwtType
import java.util.UUID

interface JwtClaimReader {
    fun getMemberId(token : String) : UUID?
    fun getEmail(token : String) : String?
    fun getAuthorities(token : String) : List<AuthorityRole>?
    fun getType(token : String) : JwtType?
}