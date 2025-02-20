package com.example.auth.application.service

import com.example.auth.application.dto.AtRtDto
import com.example.auth.application.mapper.AuthMapper
import com.example.auth.application.dto.LoginDto
import com.example.auth.application.dto.RefreshDto
import com.example.user.domain.exception.InvalidRefreshTokenException
import com.example.user.domain.service.UserLoginService
import com.example.user.domain.validator.JwtValidator
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.type.JwtType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserLoginUseCase(
    private val userLoginService: UserLoginService,
    private val jwtValidator: JwtValidator
) {
    fun login(loginDto: LoginDto) : AtRtDto {
        val atRt : AtRt = userLoginService.login(loginDto.email, loginDto.password)
        return AuthMapper.atRtToAtRtDto(atRt)
    }

    fun refresh(refreshDto: RefreshDto) : AtRtDto {
        val refreshToken = refreshDto.refreshToken
        if (!jwtValidator.isValid(refreshToken, JwtType.REFRESH)) {
            throw InvalidRefreshTokenException()
        }
        val atRt : AtRt = userLoginService.refresh(refreshToken)
        return AuthMapper.atRtToAtRtDto(atRt)
    }
}