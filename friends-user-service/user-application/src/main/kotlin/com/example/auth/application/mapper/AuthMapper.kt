package com.example.auth.application.mapper

import com.example.auth.application.dto.AtRtDto
import com.example.auth.application.dto.RegisterDto
import com.example.auth.application.dto.ValidateEmailAuthTokenDto
import com.example.user.domain.valueobject.AtRt

object AuthMapper {
    fun registerDtoToValidateEmailAuthTokenDto(registerDto: RegisterDto): ValidateEmailAuthTokenDto {
        return ValidateEmailAuthTokenDto(
            email = registerDto.email,
            token = registerDto.emailAuthToken
        )
    }

    fun atRtToAtRtDto(atRt: AtRt): AtRtDto {
        return AtRtDto(
            accessToken = atRt.accessToken,
            refreshToken = atRt.refreshToken
        )
    }
}