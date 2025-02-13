package com.auth.application.security.userdetails

import com.example.user.application.service.MemberQueryService
import com.example.user.domain.entity.Member
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class CustomUserDetailsService (
    private val memberQueryService: MemberQueryService
) : UserDetailsService{
    override fun loadUserByUsername(email: String): UserDetails {
        val member: Member = memberQueryService.getMemberByEmailWithAuthorities(email)
        return CustomUserDetails(member)
    }
}