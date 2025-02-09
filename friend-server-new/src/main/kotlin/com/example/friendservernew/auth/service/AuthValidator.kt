package com.example.friendservernew.auth.service

import com.example.friendservernew.auth.exception.EmailAlreadyExistsException
import com.example.friendservernew.auth.exception.InvalidEmailException
import com.example.friendservernew.auth.exception.InvalidNicknameException
import com.example.friendservernew.auth.exception.InvalidPasswordException
import com.example.friendservernew.member.UserDto
import com.example.friendservernew.member.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class AuthValidator(
    private val memberRepository: MemberRepository,
) {
    fun registerValidator(userDto: UserDto) {
        if (isEmailExists(userDto.email)) {
            throw EmailAlreadyExistsException()
        }
        if (!isValidEmail(userDto.email)) {
            throw InvalidEmailException()
        }
        if (!isValidNickname(userDto.nickname)) {
            throw InvalidNicknameException()
        }
        if (!isValidPasswordPattern(userDto.password)) {
            throw InvalidPasswordException()
        }
    }

    fun isEmailExists(email: String): Boolean = memberRepository.existsByEmail(email)

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$") // 이메일 형식
        return emailRegex.matches(email)
    }

    fun isValidNickname(nickname: String): Boolean {
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
