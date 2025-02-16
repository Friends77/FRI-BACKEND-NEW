package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.LoginDto
import com.example.auth.application.RefreshDto
import com.example.user.domain.service.UserLoginService
import com.example.user.domain.valueobject.AtRt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserLoginUseCase(
    private val userLoginService: UserLoginService,
) {
    fun login(loginDto: LoginDto) : AtRtDto {
        val atRt : AtRt = userLoginService.login(loginDto.email, loginDto.password)
        return AuthMapper.atRtToAtRtDto(atRt)
    }

    fun refresh(refreshDto: RefreshDto) : AtRtDto {
        val atRt : AtRt = userLoginService.refresh(refreshDto.refreshToken)
        return AuthMapper.atRtToAtRtDto(atRt)
    }
}