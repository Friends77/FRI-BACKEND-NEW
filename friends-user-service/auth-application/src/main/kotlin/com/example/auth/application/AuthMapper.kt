package com.example.auth.application

object AuthMapper {
    fun registerDtoToValidateEmailAuthTokenDto(registerDto: RegisterDto): ValidateEmailAuthTokenDto {
        return ValidateEmailAuthTokenDto(
            email = registerDto.email,
            token = registerDto.emailAuthToken
        )
    }
}