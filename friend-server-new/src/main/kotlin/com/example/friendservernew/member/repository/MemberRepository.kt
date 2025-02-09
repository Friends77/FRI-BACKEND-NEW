package com.example.friendservernew.member.repository

import com.example.friendservernew.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.mutableAuthorities WHERE m.email = :email")
    fun findByEmailWithAuthorities(email: String): Member?

    fun existsByEmail(email: String): Boolean
}
