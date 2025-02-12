package com.example.user.application.repository

import com.example.user.domain.entity.Member

interface MemberRepository {
    fun findByEmailWithAuthorities(email: String): Member?
    fun save(member: Member)
}