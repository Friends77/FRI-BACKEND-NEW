package com.example.user.domain.service

import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.auth.AtRtGenerator
import com.example.user.domain.exception.AlreadyRegisteredAnotherMethodException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.type.OAuth2ProviderType
import org.springframework.stereotype.Service

@Service
class UserOAuth2LoginService(
    private val memberRepository: MemberRepository,
    private val profileRepository: ProfileRepository,
    private val atRtGenerator: AtRtGenerator
) {
    fun loginByOAuth2(
        nickname: String,
        email: String,
        oAuth2ProviderType: OAuth2ProviderType
    ) : AtRt {
        val member = memberRepository.findByEmailWithAuthorities(email) ?: registerUserByOAuth2(nickname, email, oAuth2ProviderType)

        if (member.oAuth2ProviderType != oAuth2ProviderType) {
            throw AlreadyRegisteredAnotherMethodException()
        }

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        return atRtGenerator.createAtRt(memberId, authorities)
    }

    private fun registerUserByOAuth2(
        nickname: String,
        email: String,
        oAuth2ProviderType: OAuth2ProviderType
    ) : Member {
        val member = Member.createUserByOAuth2(email = Email(email), oAuth2ProviderType = oAuth2ProviderType)
        val profile = Profile(member = member, nickname = nickname)
        memberRepository.save(member)
        profileRepository.save(profile)
        return member
    }
}