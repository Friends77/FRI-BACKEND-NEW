package com.example.auth.application.service

import com.example.auth.application.dto.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.dto.OAuth2LoginDto
import com.example.user.domain.exception.InvalidOAuth2ProviderException
import com.example.user.domain.service.OAuth2Service
import com.example.user.domain.service.UserOAuth2LoginService
import com.example.user.domain.valueobject.OAuth2Provider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserOAuth2LoginUserCase(
    private val userOAuth2LoginService: UserOAuth2LoginService,
    private val oAuth2Service: OAuth2Service
){
    fun loginByOAuth2(oAuth2LoginDto: OAuth2LoginDto) : AtRtDto {
        val code = oAuth2LoginDto.authorizationCode
        val oAuth2Provider = getOauth2Provider(oAuth2LoginDto.oAuth2Provider)

        val oAuth2ProfileData = oAuth2Service.getUserProfile(code, oAuth2Provider)

        val nickname = oAuth2ProfileData.name
        val email = oAuth2ProfileData.email

        val atRt = userOAuth2LoginService.loginByOAuth2(nickname, email, oAuth2Provider)

        return AuthMapper.atRtToAtRtDto(atRt)
    }

    private fun getOauth2Provider(oAuth2Provider: String): OAuth2Provider {
        return try {
            OAuth2Provider.valueOf(oAuth2Provider)
        } catch (e: Exception) {
            throw InvalidOAuth2ProviderException()
        }
    }
}