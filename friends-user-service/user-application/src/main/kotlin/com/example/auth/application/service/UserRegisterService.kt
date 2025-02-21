package com.example.auth.application.service

import com.example.auth.application.dto.RegisterDto
import com.example.auth.application.dto.UserDto
import com.example.user.domain.auth.PasswordEncoder
import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.exception.AlreadyRegisteredAnotherMethodException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.validator.EmailAuthTokenValidator
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.EncodedPassword
import com.example.user.domain.valueobject.Nickname
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserRegisterService(
    private val emailAuthTokenValidator: EmailAuthTokenValidator,
    private val memberRepository: MemberRepository,
    private val profileRepository: ProfileRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun register(registerDto: RegisterDto) : UserDto {
        // 이메일 인증 토큰 검사
        val emailAuthToken = registerDto.emailAuthToken
        emailAuthTokenValidator.validateEmailAuthToken(emailAuthToken, registerDto.email)

        val email = Email(registerDto.email)
        if (memberRepository.existsByEmail(email)) {
            throw AlreadyRegisteredAnotherMethodException("이미 가입된 이메일입니다.")
        }
        val nickname = Nickname(registerDto.nickname)
        val encodedPassword = EncodedPassword(passwordEncoder.encode(registerDto.password))

        val member = Member.createUser(email = email, password = encodedPassword)
        val profile = Profile(member = member, nickname = nickname)

        memberRepository.save(member)
        profileRepository.save(profile)

        return UserDto(memberId = member.id, email = member.email.value)
    }
}