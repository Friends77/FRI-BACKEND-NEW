package com.example.user.domain.validator

import com.example.user.domain.exception.EmailAlreadyExistsException
import com.example.user.domain.exception.InvalidEmailPatternException
import com.example.user.domain.exception.InvalidNicknamePatternException
import com.example.user.domain.exception.InvalidPasswordPatternException
import com.example.user.domain.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class UserRegisterValidator(
    private val memberRepository: MemberRepository
) {
    fun validateUniqueEmail(email: String) {
        if (memberRepository.existsByEmail(email)) {
            throw EmailAlreadyExistsException()
        }
    }

    fun validateEmailPattern(email: String) {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$") // 이메일 형식
        if (!emailRegex.matches(email)) {
            throw InvalidEmailPatternException()
        }
    }

    fun validateNicknamePattern(nickname: String) {
        val lengthRegex = Regex(".{2,20}") // 길이 제한
        if (!lengthRegex.matches(nickname)) {
            throw InvalidNicknamePatternException()
        }
    }

    fun validatePasswordPattern(password: String) {
        val lengthRegex = Regex(".{8,20}") // 길이 제한
        val lowerCaseRegex = Regex(".*[a-z].*") // 소문자 포함
        val digitRegex = Regex(".*[0-9].*") // 숫자 포함
        val specialCharRegex = Regex(".*[!@#\$%^&*(),.?\":{}|<>].*") // 특수문자 포함
        val noWhiteSpaceRegex = Regex("^[^\\s]*\$") // 공백 금지

        if (!lengthRegex.matches(password) ||
            !lowerCaseRegex.matches(password) ||
            !digitRegex.matches(password) ||
            !specialCharRegex.matches(password) ||
            !noWhiteSpaceRegex.matches(password)
        ) {
            throw InvalidPasswordPatternException()
        }
    }
}