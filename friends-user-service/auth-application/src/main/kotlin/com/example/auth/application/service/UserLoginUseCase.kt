package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.LoginDto
import com.example.auth.application.RefreshDto
import com.example.user.domain.service.UserLoginService
import com.example.user.domain.validator.AtRtValidator
import com.example.user.domain.valueobject.AtRt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserLoginUseCase(
    private val userLoginService: UserLoginService,
    private val atRtValidator: AtRtValidator,
) {
    fun login(loginDto: LoginDto) : AtRtDto {
        val atRt : AtRt = userLoginService.login(loginDto.email, loginDto.password)
        return AuthMapper.atRtToAtRtDto(atRt)
    }

    fun refresh(refreshDto: RefreshDto) : AtRtDto {
        val refreshToken = refreshDto.refreshToken
        atRtValidator.validateRefreshToken(refreshToken)
        val atRt : AtRt = userLoginService.refresh(refreshToken)
        return AuthMapper.atRtToAtRtDto(atRt)
    }
}