package com.example.auth.application.service

import com.example.auth.application.RegisterDto
import com.example.auth.application.UserDto
import com.example.user.domain.service.UserRegisterService
import com.example.user.domain.validator.EmailAuthTokenValidator
import com.example.user.domain.validator.UserRegisterValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserRegisterUseCase(
    private val userRegisterService: UserRegisterService,
    private val userRegisterValidator: UserRegisterValidator,
    private val emailAuthTokenValidator: EmailAuthTokenValidator
) {
    @Transactional
    fun register(registerDto: RegisterDto) : UserDto {
        val email = registerDto.email
        val nickname = registerDto.nickname
        val password = registerDto.password
        val emailAuthToken = registerDto.emailAuthToken

        // 이메일 인증 토큰 검사
        emailAuthTokenValidator.validateEmailAuthToken(emailAuthToken, email)

        // 회원가입 정보 검사 (이메일 중복, 이메일 패턴, 닉네임 패턴, 비밀번호 패턴)
        userRegisterValidator.validateUniqueEmail(email)
        userRegisterValidator.validateNicknamePattern(nickname)
        userRegisterValidator.validateEmailPattern(email)
        userRegisterValidator.validatePasswordPattern(password)

        val createdUser = userRegisterService.registerUser(
            nickname = nickname,
            email = email,
            password = password
        )
        return UserDto(memberId = createdUser.id, email = createdUser.email)
    }
}