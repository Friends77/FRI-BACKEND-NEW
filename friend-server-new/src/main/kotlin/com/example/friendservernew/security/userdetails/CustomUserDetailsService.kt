package com.example.friendservernew.security.userdetails

import com.example.friendservernew.member.entity.Member
import com.example.friendservernew.member.service.MemberQueryService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
    private val memberQueryService: MemberQueryService,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val member: Member = memberQueryService.getMemberByEmailWithAuthorities(email)
        return CustomUserDetails(member)
    }
}
