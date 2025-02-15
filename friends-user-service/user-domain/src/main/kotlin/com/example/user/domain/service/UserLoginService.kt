package com.example.user.domain.service

import com.example.user.domain.entity.Member
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.util.PasswordEncoder
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.OAuth2Provider
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

        if (!passwordEncoder.matches(password, member.password!!)) {
            throw IllegalArgumentException("Password does not match")
        }

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        return atRtService.createAtRt(memberId, authorities)
    }

    @Transactional
    fun loginByOAuth2(
        email: String,
        oAuth2Provider: OAuth2Provider
    ) : AtRt {
        // 가입한 회원이지 확인하고 가입하지 않았다면 대신 회원 가입
        val member = memberRepository.findByEmailWithAuthorities(email) ?: Member.createUserByOAuth2(email, oAuth2Provider)

        // 이미 다른 소셜 서비스로 가입한 경우 예외 처리
        if (member.oAuth2Provider != oAuth2Provider) {
            throw IllegalArgumentException("OAuth2 provider does not match")
        }

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        return atRtService.createAtRt(memberId, authorities)
    }

}