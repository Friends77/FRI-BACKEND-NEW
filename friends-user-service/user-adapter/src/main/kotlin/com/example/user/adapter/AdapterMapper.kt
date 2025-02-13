package com.example.user.adapter

import com.example.auth.application.RegisterDto
import com.example.auth.application.UserDto


object AdapterMapper {
    fun registerRequestDtoToRegisterDto(registerRequestDto: RegisterRequestDto): RegisterDto {
        return RegisterDto(
            nickname = registerRequestDto.nickname,
            email = registerRequestDto.email,
            password = registerRequestDto.password
        )
    }

    fun userDtoToRegisterResponseDto(userDto: UserDto): RegisterResponseDto {
        return RegisterResponseDto(
            userId = userDto.memberId,
            nickname = userDto.nickname,
            email = userDto.email
        )
    }
}