package com.example.user.domain.service

import com.example.user.domain.entity.auth.AtRtSupporter
import com.example.user.domain.exception.LoginFailedException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.entity.auth.PasswordEncoder
import com.example.user.domain.valueobject.AtRt
import org.springframework.stereotype.Service

@Service
class UserLoginService(
    private val atRtSupporter: AtRtSupporter,
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(
        email: String,
        password: String,
    ) : AtRt{
        val member = memberRepository.findByEmailWithAuthorities(email) ?: throw LoginFailedException()

        if (!passwordEncoder.matches(password, member.password!!)) throw LoginFailedException()

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        return atRtSupporter.createAtRt(memberId, authorities)
    }

    fun refresh(
        refreshToken: String
    ) : AtRt {
        val memberId = atRtSupporter.getMemberId(refreshToken)
        val authorities = atRtSupporter.getAuthorities(refreshToken)
        return atRtSupporter.createAtRt(memberId, authorities)
    }
}