package com.example.friendservernew.member.service

import com.example.friendservernew.member.MemberNotFoundByEmailException
import com.example.friendservernew.member.entity.Member
import com.example.friendservernew.member.repository.MemberRepository
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