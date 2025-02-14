package com.example.user.adapter

import com.example.auth.application.AtRtDto
import com.example.auth.application.LoginDto
import com.example.auth.application.RegisterDto
import com.example.auth.application.UserDto


object AdapterMapper {
    fun registerRequestDtoToRegisterDto(registerRequestDto: RegisterRequestDto): RegisterDto {
        return RegisterDto(
            email = registerRequestDto.email,
            password = registerRequestDto.password
        )
    }

    fun userDtoToRegisterResponseDto(userDto: UserDto): RegisterResponseDto {
        return RegisterResponseDto(
            userId = userDto.memberId,
            email = userDto.email
        )
    }

    fun loginRequestDtoToLoginDto(loginRequestDto: LoginRequestDto): LoginDto {
        return LoginDto(
            email = loginRequestDto.email,
            password = loginRequestDto.password
        )
    }

    fun atRtDtoToLoginResponseDto(atRtDto: AtRtDto): LoginResponseDto {
        return LoginResponseDto(
            accessToken = atRtDto.accessToken,
            refreshToken = atRtDto.refreshToken
        )
    }
}