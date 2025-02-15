package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.LoginDto
import com.example.auth.application.OAuth2LoginDto
import com.example.auth.application.exception.LoginFailedException
import com.example.user.domain.service.OAuth2Service
import com.example.user.domain.service.UserLoginService
import com.example.user.domain.valueobject.AtRt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserLoginUseCase(
    private val userLoginService: UserLoginService,
    private val oAuth2Service: OAuth2Service
) {
    fun login(loginDto: LoginDto) : AtRtDto {
        val atRt : AtRt = try {
            userLoginService.login(loginDto.email, loginDto.password)
        } catch (e: Exception) {
            throw LoginFailedException()
        }

        return AuthMapper.atRtToAtRtDto(atRt)
    }

    fun oAuth2Login(oAuth2LoginDto: OAuth2LoginDto) : AtRtDto {
        val code = oAuth2LoginDto.authorizationCode
        val oAuth2Provider = oAuth2LoginDto.oAuth2Provider

        val userProfile = oAuth2Service.getUserProfile(
            code = code,
            oAuth2Provider = oAuth2Provider
        )

        val email = userProfile.email
        val atRt = userLoginService.loginByOAuth2(email, oAuth2Provider)
        return AuthMapper.atRtToAtRtDto(atRt)
    }
}