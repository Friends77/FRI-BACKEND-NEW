package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.CreateAtRtDto
import com.example.auth.application.LoginDto
import com.example.auth.application.RegisterDto
import com.example.auth.application.UserDto
import com.example.auth.application.security.userdetails.CustomUserDetails
import com.example.user.domain.entity.Member
import com.example.user.domain.repository.MemberRepository
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
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun register(registerDto: RegisterDto) : UserDto {
        val nickname = registerDto.nickname
        val email = registerDto.email
        val password = registerDto.password
        val member = Member(nickname = nickname, email = email, password = passwordEncoder.encode(password))
        memberRepository.save(member)
        return UserDto(memberId = member.id, nickname = member.nickname, email = member.email)
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