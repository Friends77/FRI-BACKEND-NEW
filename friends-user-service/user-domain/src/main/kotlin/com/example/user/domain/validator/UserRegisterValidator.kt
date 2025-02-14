package com.example.user.domain.validator

import com.example.user.domain.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class UserRegisterValidator(
    private val memberRepository: MemberRepository
) {
    fun isEmailExists(email: String): Boolean = memberRepository.existsByEmail(email)

    fun isValidEmailPattern(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$") // 이메일 형식
        return emailRegex.matches(email)
    }

    fun isValidNicknamePattern(nickname: String): Boolean {
        val lengthRegex = Regex(".{2,20}") // 길이 제한
        return lengthRegex.matches(nickname)
    }

    fun isValidPasswordPattern(password: String): Boolean {
        val lengthRegex = Regex(".{8,20}") // 길이 제한
        val lowerCaseRegex = Regex(".*[a-z].*") // 소문자 포함
        val digitRegex = Regex(".*[0-9].*") // 숫자 포함
        val specialCharRegex = Regex(".*[!@#\$%^&*(),.?\":{}|<>].*") // 특수문자 포함
        val noWhiteSpaceRegex = Regex("^[^\\s]*\$") // 공백 금지

        return lengthRegex.matches(password) &&
                lowerCaseRegex.containsMatchIn(password) &&
                digitRegex.containsMatchIn(password) &&
                specialCharRegex.containsMatchIn(password) &&
                noWhiteSpaceRegex.matches(password)
    }
}