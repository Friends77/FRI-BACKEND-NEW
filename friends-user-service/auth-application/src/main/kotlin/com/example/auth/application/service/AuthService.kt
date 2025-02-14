package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.CreateAtRtDto
import com.example.auth.application.LoginDto
import com.example.auth.application.RegisterDto
import com.example.auth.application.UserDto
import com.example.auth.application.security.userdetails.CustomUserDetails
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
class AuthService(
    private val atRtService: AtRtService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val memberRepository: MemberRepository,
    private val profileRepository: ProfileRepository
) {
    @Transactional
    fun register(registerDto: RegisterDto) : UserDto {
        val nickname = registerDto.nickname
        val email = registerDto.email
        val password = registerDto.password
        val member = Member.createUser(email = email, password = passwordEncoder.encode(password))
        val profile = Profile(member = member, nickname = nickname)
        memberRepository.save(member)
        profileRepository.save(profile)
        return UserDto(memberId = member.id, email = member.email, nickname = nickname)
    }

    @Transactional(readOnly = true)
    fun login(loginDto: LoginDto) : AtRtDto {
        val email = loginDto.email
        val password = loginDto.password
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))

        val userDetails = authentication.principal as CustomUserDetails
        val memberId = userDetails.memberId
        val authorities = userDetails.authorities

        val createAtRtDto = CreateAtRtDto(memberId = memberId, authorities = authorities)
        return atRtService.createAtRt(createAtRtDto)
    }
}