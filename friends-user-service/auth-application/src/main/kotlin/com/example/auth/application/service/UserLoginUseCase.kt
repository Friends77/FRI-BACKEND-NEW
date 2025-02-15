package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.LoginDto
import com.example.auth.application.exception.LoginFailedException
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
        val atRt : AtRt = try {
            userLoginService.login(loginDto.email, loginDto.password)
        } catch (e: Exception) {
            throw LoginFailedException()
        }

        return AuthMapper.atRtToAtRtDto(atRt)
    }
}