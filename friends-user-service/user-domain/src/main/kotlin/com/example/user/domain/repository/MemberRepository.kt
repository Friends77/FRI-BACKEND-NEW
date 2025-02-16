package com.example.user.domain.repository

import com.example.user.domain.entity.Member
import java.util.UUID

interface MemberRepository {
    fun findByEmailWithAuthorities(email: String): Member?

    fun save(member: Member): Member

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Member?
}