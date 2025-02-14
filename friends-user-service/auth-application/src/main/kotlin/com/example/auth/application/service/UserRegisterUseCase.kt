package com.example.auth.application.service

import com.example.auth.application.AuthMapper
import com.example.auth.application.RegisterDto
import com.example.auth.application.UserDto
import com.example.auth.application.exception.EmailAlreadyExistsException
import com.example.auth.application.exception.InvalidEmailJwtException
import com.example.auth.application.exception.InvalidEmailPatternException
import com.example.auth.application.exception.InvalidNicknamePatternException
import com.example.auth.application.exception.InvalidPasswordPatternException
import com.example.user.domain.service.EmailAuthTokenService
import com.example.user.domain.service.UserRegisterService
import com.example.user.domain.validator.UserRegisterValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserRegisterUseCase(
    private val userRegisterService: UserRegisterService,
    private val userRegisterValidator: UserRegisterValidator,
    private val emailAuthTokenService: EmailAuthTokenService,
) {
    @Transactional
    fun register(registerDto: RegisterDto) : UserDto {
        val email = registerDto.email
        val nickname = registerDto.nickname
        val password = registerDto.password
        val emailAuthToken = registerDto.emailAuthToken

        // 이메일 인증 토큰 검사
        if (!emailAuthTokenService.isValidEmailAuthToken(emailAuthToken, email)) {
            throw InvalidEmailJwtException()
        }

        // 회원가입 정보 검사 (이메일 중복, 이메일 패턴, 닉네임 패턴, 비밀번호 패턴)
        if (userRegisterValidator.isEmailExists(email)) {
            throw EmailAlreadyExistsException()
        }
        if (!userRegisterValidator.isValidNicknamePattern(nickname)) {
            throw InvalidNicknamePatternException()
        }
        if (!userRegisterValidator.isValidEmailPattern(email)) {
            throw InvalidEmailPatternException()
        }
        if (!userRegisterValidator.isValidPasswordPattern(password)) {
            throw InvalidPasswordPatternException()
        }

        val createdUser = userRegisterService.registerUser(
            nickname = nickname,
            email = email,
            password = password
        )
        return UserDto(memberId = createdUser.id, email = createdUser.email)
    }
}