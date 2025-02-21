package com.example.auth.application.service

import com.example.auth.application.dto.AtRtDto
import com.example.auth.application.mapper.AuthMapper
import com.example.auth.application.dto.OAuth2LoginDto
import com.example.user.domain.auth.AtRtGenerator
import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.exception.AlreadyRegisteredAnotherMethodException
import com.example.user.domain.exception.OAuth2DataExtractException
import com.example.user.domain.exception.OAuth2FetchFailedException
import com.example.user.domain.oauth2.OAuth2DataExtractorFactory
import com.example.user.domain.oauth2.OAuth2Fetcher
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.Nickname
import com.example.user.domain.valueobject.OAuth2ProfileData
import com.example.user.domain.valueobject.OAuth2Provider
import com.example.user.domain.valueobject.type.OAuth2ProviderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class UserOAuth2LoginService(
    private val atRtGenerator: AtRtGenerator,
    private val oAuth2Fetcher: OAuth2Fetcher,
    private val memberRepository: MemberRepository,
    private val profileRepository: ProfileRepository,
    private val oAuth2DataExtractorFactory: OAuth2DataExtractorFactory,
){
    fun loginByOAuth2(oAuth2LoginDto: OAuth2LoginDto) : AtRtDto {
        val code = oAuth2LoginDto.authorizationCode
        val oauth2Provider = OAuth2Provider(oAuth2LoginDto.oAuth2Provider)

        val oAuth2ProfileData = getUserProfile(code, oauth2Provider.type)

        val nickname = try {
            Nickname(oAuth2ProfileData.name)
        } catch (e: Exception) { // 소셜 서비스에서 가져온 닉네임이 부적절할 경우 랜덤한 닉네임으로 대체
            Nickname(UUID.randomUUID().toString().substring(0, 5))
        }
        val email = Email(oAuth2ProfileData.email)

        // 소셜 로그인으로 가입한 이력이 없다면 대신 회원가입
        val member = memberRepository.findByEmailWithAuthorities(email.value)
            ?: registerUserByOAuth2(nickname, email, oauth2Provider)
        // 똑같은 이메일로 가입한 이력이 존재하다면 예외 처리
        if (member.oAuth2Provider != oauth2Provider) {
            throw AlreadyRegisteredAnotherMethodException("이미 ${member.oAuth2Provider?.type} 로 가입한 이력이 있습니다.")
        }
        val atRt = atRtGenerator.createAtRt(member.id, member.authorities.map { it.role })
        return AuthMapper.atRtToAtRtDto(atRt)
    }

    private fun getUserProfile(
        code: String,
        oAuth2ProviderType: OAuth2ProviderType
    ): OAuth2ProfileData {
        val accessToken = oAuth2Fetcher.getAccessToken(code, oAuth2ProviderType)
            ?: throw OAuth2FetchFailedException("${oAuth2ProviderType.name} 에서 access token 을 가져오는데 실패했습니다.")
        val attributes = oAuth2Fetcher.getUserAttributes(accessToken, oAuth2ProviderType)
            ?: throw OAuth2FetchFailedException("${oAuth2ProviderType.name} 에서 사용자 정보를 가져오는데 실패했습니다.")

        val extractor = oAuth2DataExtractorFactory.getExtractor(oAuth2ProviderType)
        return try {
            extractor.extract(attributes)
        } catch (e: Exception) {
            throw OAuth2DataExtractException("사용자 정보를 추출하는데 실패했습니다. ${e.message}")
        }
    }

    private fun registerUserByOAuth2(
        nickname: Nickname,
        email: Email,
        oAuth2Provider: OAuth2Provider
    ) : Member {
        val member = Member.createUserByOAuth2(email = email, oAuth2Provider = oAuth2Provider)
        val profile = Profile(member = member, nickname = nickname)
        memberRepository.save(member)
        profileRepository.save(profile)
        return member
    }
}