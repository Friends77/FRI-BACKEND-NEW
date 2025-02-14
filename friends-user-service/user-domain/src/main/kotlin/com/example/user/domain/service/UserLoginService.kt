package com.example.user.domain.service

import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.util.PasswordEncoder
import com.example.user.domain.valueobject.AtRt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserLoginService(
    private val atRtService: AtRtService,
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(
        email: String,
        password: String,
    ) : AtRt{
        val member = memberRepository.findByEmailWithAuthorities(email)
            ?: throw IllegalArgumentException("Member not found for email: $email")

        if (!passwordEncoder.matches(password, member.password)) {
            throw IllegalArgumentException("Password does not match")
        }

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        return atRtService.createAtRt(memberId, authorities)
    }

}