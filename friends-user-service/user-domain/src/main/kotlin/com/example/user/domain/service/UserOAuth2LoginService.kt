package com.example.user.domain.service

import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.entity.auth.AtRtSupporter
import com.example.user.domain.exception.AlreadyRegisteredAnotherMethodException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.OAuth2Provider
import org.springframework.stereotype.Service

@Service
class UserOAuth2LoginService(
    private val memberRepository: MemberRepository,
    private val profileRepository: ProfileRepository,
    private val atRtSupporter: AtRtSupporter
) {
    fun loginByOAuth2(
        nickname: String,
        email: String,
        oAuth2Provider: OAuth2Provider
    ) : AtRt {
        val member = memberRepository.findByEmailWithAuthorities(email) ?: registerUserByOAuth2(nickname, email, oAuth2Provider)

        if (member.oAuth2Provider != oAuth2Provider) {
            throw AlreadyRegisteredAnotherMethodException()
        }

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        return atRtSupporter.createAtRt(memberId, authorities)
    }

    private fun registerUserByOAuth2(
        nickname: String,
        email: String,
        oAuth2Provider: OAuth2Provider
    ) : Member {
        val member = Member.createUserByOAuth2(email = email, oAuth2Provider = oAuth2Provider)
        val profile = Profile(member = member, nickname = nickname)
        memberRepository.save(member)
        profileRepository.save(profile)
        return member
    }
}