package com.auth.application.security.userdetails

import com.auth.application.exception.MemberNotFoundByEmailException
import com.example.user.domain.entity.Member
import com.example.user.domain.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService (
    private val memberRepository: MemberRepository
) : UserDetailsService{
    override fun loadUserByUsername(email: String): UserDetails {
        val member: Member = memberRepository.findByEmailWithAuthorities(email) ?: throw MemberNotFoundByEmailException()
        return CustomUserDetails(member)
    }
}