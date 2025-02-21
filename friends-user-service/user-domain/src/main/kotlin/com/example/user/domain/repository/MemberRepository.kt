package com.example.user.domain.repository

import com.example.user.domain.entity.Member
import com.example.user.domain.valueobject.Email
import java.util.UUID

interface MemberRepository {
    fun findByEmailWithAuthorities(email: Email): Member?

    fun save(member: Member): Member

    fun existsByEmail(email: Email): Boolean

    fun findByEmail(email: Email): Member?
}