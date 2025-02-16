package com.example.user.adapter

import com.example.auth.application.AtRtDto
import com.example.auth.application.CreateEmailAuthTokenDto
import com.example.auth.application.LoginDto
import com.example.auth.application.OAuth2LoginDto
import com.example.auth.application.RegisterDto
import com.example.auth.application.ResetPasswordDto
import com.example.auth.application.UserDto


object AdapterMapper {
    fun registerRequestDtoToRegisterDto(registerRequestDto: RegisterRequestDto): RegisterDto {
        return RegisterDto(
            emailAuthToken = registerRequestDto.emailAuthToken,
            nickname = registerRequestDto.nickname,
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

    fun emailAuthTokenRequestDtoToCreateEmailAuthTokenDto(emailAuthTokenRequestDto: EmailAuthTokenRequestDto): CreateEmailAuthTokenDto {
        return CreateEmailAuthTokenDto(
            email = emailAuthTokenRequestDto.email,
            code = emailAuthTokenRequestDto.code
        )
    }

    fun oauth2LoginRequestDtoToOAuth2LoginDto(oauth2LoginRequestDto: OAuth2LoginRequestDto): OAuth2LoginDto {
        return OAuth2LoginDto(
            authorizationCode = oauth2LoginRequestDto.authorizationCode,
            oAuth2Provider = oauth2LoginRequestDto.oauth2Provider
        )
    }

    fun resetPasswordRequestDtoToResetPasswordDto(resetPasswordRequestDto: ResetPasswordRequestDto): ResetPasswordDto {
        return ResetPasswordDto(
            emailAuthToken = resetPasswordRequestDto.emailAuthToken,
            email = resetPasswordRequestDto.email,
            newPassword = resetPasswordRequestDto.newPassword
        )
    }
}