package com.example.user.application.service

import com.example.user.application.exception.MemberNotFoundByEmailException
import com.example.user.application.repository.MemberRepository
import com.example.user.domain.entity.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService (
    private val memberRepository: MemberRepository
){
    fun getMemberByEmailWithAuthorities(email: String): Member =
        memberRepository.findByEmailWithAuthorities(email) ?: throw MemberNotFoundByEmailException()
}