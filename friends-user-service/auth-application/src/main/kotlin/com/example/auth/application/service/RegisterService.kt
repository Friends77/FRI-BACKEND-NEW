package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.AuthMapper
import com.example.auth.application.CreateAtRtDto
import com.example.auth.application.LoginDto
import com.example.auth.application.RegisterDto
import com.example.auth.application.UserDto
import com.example.auth.application.security.userdetails.CustomUserDetails
import com.example.auth.application.validation.AuthValidator
import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterService(
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository,
    private val profileRepository: ProfileRepository,
    private val emailAuthTokenService: EmailAuthTokenService,
    private val authValidator: AuthValidator
) {
    @Transactional
    fun register(registerDto: RegisterDto) : UserDto {
        // 이메일 인증 토큰 검사
        val validateEmailAuthTokenDto = AuthMapper.registerDtoToValidateEmailAuthTokenDto(registerDto)
        emailAuthTokenService.validateEmailAuthToken(validateEmailAuthTokenDto)

        // 회원가입 정보 검사 (이메일 패턴, 닉네임 패턴, 비밀번호 패턴)
        authValidator.registerValidator(registerDto)

        val nickname = registerDto.nickname
        val email = registerDto.email
        val encodedPassword = passwordEncoder.encode(registerDto.password)
        val member = Member.createUser(email = email, password = encodedPassword)
        val profile = Profile(member = member, nickname = nickname)
        memberRepository.save(member)
        profileRepository.save(profile)
        return UserDto(memberId = member.id, email = member.email, nickname = nickname)
    }
}