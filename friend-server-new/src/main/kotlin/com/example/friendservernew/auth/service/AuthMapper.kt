package com.example.friendservernew.auth.service

import com.example.friendservernew.auth.controller.LoginResponseDto
import com.example.friendservernew.auth.controller.RefreshResponseDto
import com.example.friendservernew.auth.controller.RegisterRequestDto
import com.example.friendservernew.member.CreateUserDto
import com.example.friendservernew.member.UserDto


object AuthMapper {
    fun registerRequestDtoToUserDto(registerRequestDto: RegisterRequestDto): UserDto =
        UserDto(
            nickname = registerRequestDto.nickname,
            email = registerRequestDto.email,
            password = registerRequestDto.password,
        )

    fun atRtDtoToLoginResponseDto(atRtDto: AtRtDto): LoginResponseDto =
        LoginResponseDto(
            accessToken = atRtDto.accessToken,
            refreshToken = atRtDto.refreshToken,
        )

    fun atRtDtoToRefreshResponseDto(atRtDto: AtRtDto): RefreshResponseDto =
        RefreshResponseDto(
            accessToken = atRtDto.accessToken,
            refreshToken = atRtDto.refreshToken,
        )

    fun registerRequestDtoToValidateEmailAuthTokenDto(registerRequestDto: RegisterRequestDto): ValidateEmailAuthTokenDto =
        ValidateEmailAuthTokenDto(
            token = registerRequestDto.emailAuthToken,
            email = registerRequestDto.email,
        )

    fun userDtoToCreateUserDto(
        userDto: UserDto,
        encodedPassword: String,
    ): CreateUserDto =
        CreateUserDto(
            nickname = userDto.nickname,
            email = userDto.email,
            encodedPassword = encodedPassword,
        )
}
