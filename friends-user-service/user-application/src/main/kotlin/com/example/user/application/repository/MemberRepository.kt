package com.example.user.application.repository

import com.example.user.domain.entity.Member
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository {
    fun findByEmailWithAuthorities(email: String): Member?
    fun save(member: Member)
}