package com.auth.application.service

import com.auth.application.exception.MemberNotFoundByEmailException
import com.example.user.domain.entity.Member
import com.example.user.domain.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberQueryService (
    private val memberRepository: MemberRepository
){
    fun getMemberByEmailWithAuthorities(email: String): Member =
        memberRepository.findByEmailWithAuthorities(email) ?: throw MemberNotFoundByEmailException()
}