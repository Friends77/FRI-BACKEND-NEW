package com.example.user.domain.repository

import com.example.user.domain.entity.Member
import com.example.user.domain.valueobject.Email
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface MemberRepository : JpaRepository<Member, UUID> {
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.mutableAuthorities WHERE m.email = :email")
    fun findByEmailWithAuthorities(email: Email): Member?

    fun existsByEmail(email: Email): Boolean

    fun findByEmail(email: Email): Member?
}