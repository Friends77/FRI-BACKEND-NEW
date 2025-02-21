package com.example.user.infrastructure.repository

import com.example.user.domain.entity.Member
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.valueobject.Email
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface MemberRepositoryImpl : JpaRepository<Member, UUID>, MemberRepository {
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.mutableAuthorities WHERE m.email = :email")
    override fun findByEmailWithAuthorities(email: Email): Member?
}