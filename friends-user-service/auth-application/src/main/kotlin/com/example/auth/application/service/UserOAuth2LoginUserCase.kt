package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.OAuth2LoginDto
import com.example.auth.application.exception.AlreadyRegisteredAnotherMethodException
import com.example.auth.application.exception.InvalidOAuth2ProviderException
import com.example.auth.application.exception.OAuth2FetchFailedException
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
        val oAuth2Provider = try {
            OAuth2Provider.valueOf(oAuth2LoginDto.oAuth2Provider)
        } catch (e: Exception) {
            throw InvalidOAuth2ProviderException()
        }

        val oAuth2ProfileData = try {
            oAuth2Service.getUserProfile(code, oAuth2Provider)
        } catch (e: Exception) {
            throw OAuth2FetchFailedException(oAuth2Provider)
        }
        val nickname = oAuth2ProfileData.name
        val email = oAuth2ProfileData.email

        val atRt = try {
            userOAuth2LoginService.loginByOAuth2(nickname, email, oAuth2Provider)
        } catch (e: Exception) {
            throw AlreadyRegisteredAnotherMethodException()
        }
        return AuthMapper.atRtToAtRtDto(atRt)
    }
}