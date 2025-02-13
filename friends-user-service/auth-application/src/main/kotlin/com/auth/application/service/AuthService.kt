package com.auth.application.service

import com.auth.application.RegisterRequestDto
import com.example.user.domain.entity.Member
import com.example.user.domain.repository.MemberRepository
import org.springframework.security.authentication.AuthenticationManager
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
    fun register(registerRequestDto: RegisterRequestDto) {
        val nickname = registerRequestDto.nickname
        val email = registerRequestDto.email
        val password = registerRequestDto.password
        val member = Member(nickname = nickname, email = email, password = passwordEncoder.encode(password))
        memberRepository.save(member)
    }
}